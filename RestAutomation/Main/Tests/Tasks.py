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
url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'TASK')

class tasks(unittest.TestCase):

    static=0
    try:
        global row
        row = Settings.myList[static]
    except IndexError:
        print IndexError
    o=0
    obj=0
    static += 1

    def Task_Post(self, ):


        print("Test Scenario : testTaskPost : started -- ")
        expectedResult = "Expected Result : " + str(row[2])
        get = {'shortDescription': str(row[2])}
        print(expectedResult)
        response = requests.post(url, data=json.dumps(get), auth=(username,password))
        tasks.o = json.loads(response.text)
        actualResult = "Actual Result :" + tasks.o["shortDescription"]
        print  actualResult
        assert (str(row[2]) == tasks.o["shortDescription"]), "Test Scenario Result : testTaskPost : FAILED"
        print("Test Scenario Result : testTaskPost : SUCCESS ")



    def Task_Get(self, ):
        #expectedResult = "Expected Result : " + str(row[2])
        print("Test Scenario : testTaskGet : started -- ")
        expectedResult = "Expected Result : " + str(row[2])
        #url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'TASK')
        print(expectedResult)

        response = requests.get(url + str(tasks.o["primaryKey"]), auth=(username, password))
        o = json.loads(response.text)
        actualResult = "Actual Result :" + o["shortDescription"]
        print actualResult
        assert (str(row[2]) == o["shortDescription"]), "Test Scenario Result : testTaskGet : FAILED"
        print("Test Scenario Result : testTaskGet : SUCCESS ")



    def Task_Put(self, ):
        print("Test Scenario : testTaskPut : started -- ")
        expectedResult = "Expected Result : " + str(row[5])
        print(expectedResult)
        get = {'shortDescription': str(row[5])}
        response = requests.put(url + str(tasks.o["primaryKey"]), data=json.dumps(get), auth=(username,password))
        tasks.obj = json.loads(response.text)
        actualResult = "Actual Result :" + tasks.obj["shortDescription"]
        print  actualResult
        assert (str(row[5]) == tasks.obj["shortDescription"]), "Test Scenario Result : testTaskPut : FAILED"
        print("Test Scenario Result : testTaskPut : SUCCESS ")



    def Task_Delete(self, ):
        print("Test Scenario : testTaskDelete : started -- ")
        print("Expected Result : " + "200")
        response = requests.delete(url + str(tasks.obj["primaryKey"]), auth=(username,password))
        print("Actual Result : " + str(response.status_code))
        assert (int(response.status_code) == 200), "Test Scenario Result : testTaskDelete : FAILED"
        print("Test Scenario Result : testTaskDelete : SUCCESS ")























