import csv, os
from Main.Utility import Settings
from ConfigParser import ConfigParser



#setting.init()

def stuff():
    try:
        cfg = ConfigParser()
        path = os.path.abspath("")
        path1 = os.path.dirname(os.path.dirname(path)) + '\Config\Config.ini'
        cfg.read(path1)
        path2 = os.path.dirname(os.path.dirname(path)) + '\TDD' + cfg.get('config1', 'DATA_FILE')
        with open(path2, 'r') as csvfile:
            global row, i
            i = 0
            row = 0
            r = csv.reader(csvfile, delimiter=',')
            for row in r:
                if (i == 0):
                    i += 1
                    continue
                Settings.myList.append(row)
    except IOError as ex:
        print 'errno:', ex.errno
        #print 'err code:', e.errorcode[e.errno]
        print ex





    
    
    
