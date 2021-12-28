import pyodbc

try:
    conn = pyodbc.connect('Driver={SQL Server};'
                          'Server=LAPTOP-3S551JLQ;'
                          'Database=assignment1_db;'
                          'Trusted_Connection=yes;')
    cursor = conn.cursor()
    cursor.execute('SELECT * FROM assignment1_db.dbo.input')
    for row in cursor:
        print(row)
except:
    print('fail to access to database!')

conn.close()