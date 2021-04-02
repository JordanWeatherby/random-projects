
# coding: utf-8

# In[37]:


positions = []
try:
    f=open('poker.txt',"r")
    for line in f:
        positions.append(line.strip('\n').split())
except:
    print("Failed to read file poker.txt")


# In[12]:


def calcelo(posList,eloDict={}):
    k=32 #higher k = more elo swing per calc
    d=400
    startingelo = 1000
    
    newelo = eloDict.copy()
    e = []
    numParts = len(posList)
    for i in range(0, numParts):
        expected = 0
        for j in range(0, numParts):
            if posList[j] not in eloDict:
                eloDict[posList[j]] = startingelo
            if i != j:
                expected += estfrac(eloDict[posList[i]], eloDict[posList[j]], d)
        expected /= (numParts*(numParts-1))/2
        e.append(expected)

    newRatings=[]

    for i in range(0, numParts):
        nr = eloDict[posList[i]] + k*(scorefunc(numParts,i+1) - e[i])
        newRatings.append(nr)

    for i in range(0,len(newRatings)):
        newelo[posList[i]] = int(newRatings[i])

    return newelo


# In[13]:


def estfrac(r1, r2, d):
    return 1/(1+(10**((r1-r2)/d)))

def scorefunc(n, p):
    return (n-p)/(n*(n-1)/2)


# In[36]:


newelo = {}
for num, game in enumerate(positions, start=1):
    newelo = calcelo(game, newelo)
    print("session "+str(num)+":")
    sort_orders = sorted(newelo.items(), key=lambda x: x[1], reverse=True)
    for i in sort_orders:
        print(i[0], i[1])
    print('-----------')

