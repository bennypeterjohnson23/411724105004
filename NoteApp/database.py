import mysql.connector


def get_connection():
    return mysql.connector.connect(
        host="localhost",
        user="root",
        password="Benny_681",
        database="note_app"
    )


def create_tables():
    connection = get_connection()
    cursor = connection.cursor()

    cursor.execute(
        """
        CREATE TABLE IF NOT EXISTS users (
            id INT AUTO_INCREMENT PRIMARY KEY,
            username VARCHAR(255) NOT NULL UNIQUE,
            password VARCHAR(255) NOT NULL
        )
        """
    )

    cursor.execute(
        """
        CREATE TABLE IF NOT EXISTS notes (
            id INT AUTO_INCREMENT PRIMARY KEY,
            user_id INT NOT NULL,
            title VARCHAR(255) NOT NULL,
            content TEXT NOT NULL,
            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
            FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
        )
        """
    )

    connection.commit()
    cursor.close()
    connection.close()


def create_user(username, password):
    connection = get_connection()
    cursor = connection.cursor()

    query = "INSERT INTO users (username, password) VALUES (%s, %s)"
    cursor.execute(query, (username, password))
    connection.commit()

    cursor.close()
    connection.close()


def get_user_by_credentials(username, password):
    connection = get_connection()
    cursor = connection.cursor(dictionary=True)

    query = "SELECT id, username FROM users WHERE username = %s AND password = %s"
    cursor.execute(query, (username, password))
    user = cursor.fetchone()

    cursor.close()
    connection.close()
    return user


def get_user_by_username(username):
    connection = get_connection()
    cursor = connection.cursor(dictionary=True)

    query = "SELECT id, username FROM users WHERE username = %s"
    cursor.execute(query, (username,))
    user = cursor.fetchone()

    cursor.close()
    connection.close()
    return user


def add_note(user_id, title, content):
    connection = get_connection()
    cursor = connection.cursor()

    query = "INSERT INTO notes (user_id, title, content) VALUES (%s, %s, %s)"
    cursor.execute(query, (user_id, title, content))
    connection.commit()

    cursor.close()
    connection.close()


def get_notes(user_id, search=None):
    connection = get_connection()
    cursor = connection.cursor(dictionary=True)

    if search:
        query = "SELECT id, title, content, created_at FROM notes WHERE user_id = %s AND (title LIKE %s OR content LIKE %s) ORDER BY created_at DESC"
        search_value = f"%{search}%"
        cursor.execute(query, (user_id, search_value, search_value))
    else:
        query = "SELECT id, title, content, created_at FROM notes WHERE user_id = %s ORDER BY created_at DESC"
        cursor.execute(query, (user_id,))

    notes = cursor.fetchall()

    cursor.close()
    connection.close()
    return notes


def get_note_by_id(note_id, user_id):
    connection = get_connection()
    cursor = connection.cursor(dictionary=True)

    query = "SELECT id, title, content, created_at FROM notes WHERE id = %s AND user_id = %s"
    cursor.execute(query, (note_id, user_id))
    note = cursor.fetchone()

    cursor.close()
    connection.close()
    return note


def update_note(note_id, user_id, title, content):
    connection = get_connection()
    cursor = connection.cursor()

    query = "UPDATE notes SET title = %s, content = %s WHERE id = %s AND user_id = %s"
    cursor.execute(query, (title, content, note_id, user_id))
    connection.commit()

    cursor.close()
    connection.close()


def delete_note(note_id, user_id):
    connection = get_connection()
    cursor = connection.cursor()

    query = "DELETE FROM notes WHERE id = %s AND user_id = %s"
    cursor.execute(query, (note_id, user_id))
    connection.commit()

    cursor.close()
    connection.close()


if __name__ == "__main__":
    connection = get_connection()

    if connection.is_connected():
        print("Connected to MySQL successfully!")

    connection.close()