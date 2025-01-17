const mysql = require('mysql')

const pool = mysql.createPool({
  host: 'localhost',
  password: '',
  user: 'root',
  database: 'watermeter',
  port: '3306',
  connectionLimit: 10,
  multipleStatements: true
})

pool.getConnection((err, connection) => {
  if (err) {
    if (err.code === 'PROTOCOL_CONNECTION_LOST') {
      console.error('Database connection was closed.')
    }
    if (err.code === 'ER_CON_COUNT_ERROR') {
      console.error('Database has too many connections.')
    }
    if (err.code === 'ECONNREFUSED') {
      console.error('Database connection was refused.')
    }
  }
  if (connection) connection.release()
})

module.exports = pool
