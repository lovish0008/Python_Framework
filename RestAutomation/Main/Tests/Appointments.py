import json,os
import requests
import time
import unittest
from datetime import datetime, timedelta
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
url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'APPOINTMENT')

class appointments(unittest.TestCase):

    static=0
    try:
        global row
        row = Settings.myList[static]
    except IndexError:
        print IndexError
    o=0
    obj=0
    static += 1

    def Appointment_Post(self, ):


        print("Test Scenario : testAppointmentPost : started -- ")
        expectedResult = "Expected Result : " + str(row[6])
        get = {'shortDescription': str(row[2])}
        print(expectedResult)
        subject = str(row[6])
        startOn = str(time.strftime("%x")) + " " + str(time.strftime("%X"))
        endOn = str(time.strftime("%x")) + " " + str('{:%H:%M:%S}'.format(
            datetime.now() + timedelta(hours=1)))
        payload = {"subject": subject,
                   "startOn": startOn,
                   "endOn": endOn,
                   "attendees": [
                       {
                           "user": {"href": "/users/3"}, "attendanceType": "WILL_ATTEND"
                       }
                   ]
                   }
        response = requests.post(url, data=json.dumps(payload), auth=(username, password))
        appointments.o = json.loads(response.text)
        actualResult = "Actual Result :" + appointments.o["subject"]
        print actualResult
        assert (str(row[6]) == appointments.o["subject"]), "Test Scenario Result : testappointmentPost : FAILED"
        print("Test Scenario Result : testAppointmentPost : SUCCESS ")



    def Appointment_Get(self, ):
        #expectedResult = "Expected Result : " + str(row[2])
        print("Test Scenario : testAppointmentGet : started -- ")
        expectedResult = "Expected Result : " + str(row[6])
        #url = cfg.get('config1', 'APPLICATION_LINK') + cfg.get('config1', 'TASK')
        print(expectedResult)

        response = requests.get(url + str(appointments.o["primaryKey"]), auth=(username, password))
        o = json.loads(response.text)
        actualResult = "Actual Result :" + o["subject"]
        print actualResult
        assert (str(row[6]) == o["subject"]), "Test Scenario Result : testTaskGet : FAILED"
        print("Test Scenario Result : testAppointmentGet : SUCCESS ")



    def Appointment_Put(self, ):
        print("Test Scenario : testAppointmentPut : started -- ")
        expectedResult = "Expected Result : " + str(row[9])
        print(expectedResult)
        subject = str(row[9])
        startOn = str(time.strftime("%x")) + " " + str(time.strftime("%X"))
        endOn = str(time.strftime("%x")) + " " + str('{:%H:%M:%S}'.format(
            datetime.now() + timedelta(hours=1)))
        payload = {"subject": subject,
                   "startOn": startOn,
                   "endOn": endOn,
                   "attendees": [
                       {
                           "user": {"href": "/users/3"}, "attendanceType": "WILL_ATTEND"
                       }
                   ]
                   }
        response = requests.put(url + str(appointments.o["primaryKey"]), data=json.dumps(payload), auth=(username, password))
        appointments.obj = json.loads(response.text)
        actualResult = "Actual Result :" + appointments.obj["subject"]
        print actualResult
        assert (str(row[9]) == appointments.obj["subject"]), "Test Scenario Result : testAppointmentPut : FAILED"
        print("Test Scenario Result : testAppointmentPut : SUCCESS ")



    def Appointment_Delete(self, ):
        print("Test Scenario : testAppointmentDelete : started -- ")
        print("Expected Result : " + "200")
        response = requests.delete(url + str(appointments.obj["primaryKey"]), auth=(username,password))
        print("Actual Result : " + str(response.status_code))
        assert (int(response.status_code) == 200), "Test Scenario Result : testAppointmentDelete : FAILED"
        print("Test Scenario Result : testAppointmentDelete : SUCCESS ")





























