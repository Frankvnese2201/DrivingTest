import collections
from pymongo import MongoClient
import pandas as pd 


class DataProcessor:
    def  __init__(self) -> None:
        url =""    #Mongo URL
        cluster=MongoClient(url)
        db = cluster[""]
        collection=db[""]

    def readData(url):
        df = pd.read_csv(url)   #log action 
        return df
    
    def insertData():
        pass

