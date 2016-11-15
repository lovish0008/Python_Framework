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
url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'DISP_PROJECT')

class projects(unittest.TestCase):

    static=0
    try:
        global row
        row = Settings.myList[static]
    except IndexError:
        print IndexError
    o=0
    obj=0
    static += 1

    def Project_Post(self, ):


        print("Test Scenario : testProjectPost : started -- ")
        expectedResult = "Expected Result : " + str(row[14])
        get = {'name': str(row[14]), 'idNumber': str(row[15]), 'entityType': 'DISP'}
        print(expectedResult)
        response = requests.post(url, data=json.dumps(get), auth=(username,password))
        projects.o = json.loads(response.text)
        actualResult = "Actual Result :" + projects.o["name"]
        print actualResult
        assert (str(row[14]) == projects.o["name"]), "Test Scenario Result : testProjectPost : FAILED"
        print("Test Scenario Result : testProjectPost : SUCCESS ")



    def Project_Get(self, ):
        #expectedResult = "Expected Result : " + str(row[2])
        print("Test Scenario : testProjectGet : started -- ")
        expectedResult = "Expected Result : " + str(row[14])
        #url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'TASK')
        print(expectedResult)

        response = requests.get(url + str(projects.o["primaryKey"]), auth=(username, password))
        o = json.loads(response.text)
        actualResult = "Actual Result :" + o["name"]
        print actualResult
        assert (str(row[14]) == o["name"]), "Test Scenario Result : testProjectGet : FAILED"
        print("Test Scenario Result : testProjectGet : SUCCESS ")



    def Project_Put(self, ):
        print("Test Scenario : testProjectPut : started -- ")
        expectedResult = "Expected Result : " + str(row[18])
        print(expectedResult)
        get = {'name': str(row[18]), 'idNumber': str(row[19]), 'entityType': 'DISP'}
        response = requests.put(url + str(projects.o["primaryKey"]), data=json.dumps(get), auth=(username,password))
        projects.obj = json.loads(response.text)
        actualResult = "Actual Result :" + projects.obj["name"]
        print  actualResult
        assert (str(row[18]) == projects.obj["name"]), "Test Scenario Result : testProjectPut : FAILED"
        print("Test Scenario Result : testProjectPut : SUCCESS ")



    def Project_Delete(self, ):
        print("Test Scenario : testProjectDelete : started -- ")
        print("Expected Result : " + "200")
        response = requests.delete(url + str(projects.obj["primaryKey"]), auth=(username,password))
        print("Actual Result : " + str(response.status_code))
        assert (int(response.status_code) == 200), "Test Scenario Result : testProjectDelete : FAILED"
        print("Test Scenario Result : testProjectDelete : SUCCESS ")





















