import json,os
import requests
import unittest
from ConfigParser import ConfigParser
from Main.Utility import FrameworkUtil
from Main.Utility import Settings

Settings.init()
global cfg,url
FrameworkUtil.stuff()
cfg = ConfigParser()
path = os.path.abspath("")
path1 = os.path.dirname(os.path.dirname(path)) + '\Config\Config.ini'
cfg.read(path1)
username = cfg.get('config1', 'LOGIN_username')
password = cfg.get('config1', 'LOGIN_password')
url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'DOCUMENT')

class documents(unittest.TestCase):

    static=0
    try:
        global row
        row = Settings.myList[static]
    except IndexError:
        print IndexError
    o=0
    obj=0
    static += 1

    def Document_Post(self, ):


        print("Test Scenario : testDocumentPost : started -- ")
        expectedResult = "Expected Result : " + str(row[10])
        get = {'name': str(row[10]), 'type': 'FOLDER'}
        print(expectedResult)
        response = requests.post(url, data=json.dumps(get), auth=(username,password))
        documents.o = json.loads(response.text)
        actualResult = "Actual Result :" + documents.o["name"]
        print  actualResult
        assert (str(row[10]) == documents.o["name"]), "Test Scenario Result : testDocumentPost : FAILED"
        print("Test Scenario Result : testDocumentPost : SUCCESS ")



    def Document_Get(self, ):
        #expectedResult = "Expected Result : " + str(row[2])
        print("Test Scenario : testDocumentGet : started -- ")
        expectedResult = "Expected Result : " + str(row[10])
        #url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'TASK')
        print(expectedResult)

        response = requests.get(url + str(documents.o["primaryKey"]), auth=(username, password))
        o = json.loads(response.text)
        actualResult = "Actual Result :" + o["name"]
        print actualResult
        assert (str(row[10]) == o["name"]), "Test Scenario Result : testDocumentGet : FAILED"
        print("Test Scenario Result : testDocumentGet : SUCCESS ")



    def Document_Put(self, ):
        print("Test Scenario : testDocumentPut : started -- ")
        expectedResult = "Expected Result : " + str(row[13])
        print(expectedResult)
        get = {'name': str(row[13]), 'type': 'FOLDER'}
        response = requests.put(url + str(documents.o["primaryKey"]), data=json.dumps(get), auth=(username,password))
        documents.obj = json.loads(response.text)
        actualResult = "Actual Result :" + documents.obj["name"]
        print actualResult
        assert (str(row[13]) == documents.obj["name"]), "Test Scenario Result : testDocumentPut : FAILED"
        print("Test Scenario Result : testDocumentPut : SUCCESS ")



    def Document_Delete(self, ):
        print("Test Scenario : testDocumentDelete : started -- ")
        print("Expected Result : " + "200")
        response = requests.delete(url + str(documents.obj["primaryKey"]), auth=(username,password))
        print("Actual Result : " + str(response.status_code))
        assert (int(response.status_code) == 200), "Test Scenario Result : testDocumentDelete : FAILED"
        print("Test Scenario Result : testDocumentDelete : SUCCESS ")































