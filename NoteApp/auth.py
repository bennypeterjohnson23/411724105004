from database import get_connection


def register():
    print("\n===== CREATE ACCOUNT =====")

    username = input("Enter username: ")
    password = input("Enter password: ")

    connection = get_connection()
    cursor = connection.cursor()

    query = """
        INSERT INTO users (username, password)
        VALUES (%s, %s)
    """

    try:
        cursor.execute(query, (username, password))
        connection.commit()

        print("\nAccount created successfully! ✅")

    except Exception as e:
        print("\nError:", e)

    finally:
        cursor.close()
        connection.close()


def login():
    print("\n===== LOGIN =====")

    username = input("Enter username: ")
    password = input("Enter password: ")

    connection = get_connection()
    cursor = connection.cursor()

    query = """
        SELECT id, username
        FROM users
        WHERE username = %s AND password = %s
    """

    cursor.execute(query, (username, password))

    user = cursor.fetchone()

    cursor.close()
    connection.close()

    if user:
        print("\nLogin successful! ✅")
        print("Welcome,", user[1])

        return user

    else:
        print("\nInvalid username or password! ❌")
        return None


if __name__ == "__main__":
    login()