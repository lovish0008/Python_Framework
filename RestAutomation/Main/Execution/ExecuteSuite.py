import csv
from Main.Tests.Tasks import tasks
from Main.Tests.Appointments import appointments
from Main.Tests.Projects import projects
from Main.Tests.Documents import documents
import os



from HTMLTestRunner import HTMLTestRunner
import unittest
from ConfigParser import ConfigParser

if __name__ == "__main__":
    cfg = ConfigParser()
    suite = unittest.TestSuite()
    path1 = os.path.abspath("")
    path2 = str(os.path.dirname(os.path.dirname(path1)) + '\Config\Config.ini')
    cfg.read(path2)
    path3 = str(os.path.dirname(os.path.dirname(path1)) + '\TDD' + cfg.get('config1', 'DATA_FILE'))
    CrudeReport11 = os.path.dirname(os.path.dirname(path1)) + '\Output' + cfg.get('config1', 'REPORT_FILE')

    outfile = file(CrudeReport11, 'w')
    runner = HTMLTestRunner(stream=outfile,
                            title='Test Report',
                            description='')

    with open(path3, 'r') as csvfile:
        r = csv.reader(csvfile, delimiter=',')
        i = 0
        for row in r:
            if (i == 0):
                i += 1
                continue
            suite.addTest(tasks("Task_Post"))
            suite.addTest(tasks("Task_Get"))
            suite.addTest(tasks("Task_Put"))
            suite.addTest(tasks("Task_Delete"))

            suite.addTest(appointments("Appointment_Post"))
            suite.addTest(appointments("Appointment_Get"))
            suite.addTest(appointments("Appointment_Put"))
            suite.addTest(appointments("Appointment_Delete"))

            suite.addTest(documents("Document_Post"))
            suite.addTest(documents("Document_Get"))
            suite.addTest(documents("Document_Put"))
            suite.addTest(documents("Document_Delete"))

            suite.addTest(projects("Project_Post"))
            suite.addTest(projects("Project_Get"))
            suite.addTest(projects("Project_Put"))
            suite.addTest(projects("Project_Delete"))
            unittest.TextTestRunner().run(suite)

            # runner.run(suite)

        runner.run(suite)





