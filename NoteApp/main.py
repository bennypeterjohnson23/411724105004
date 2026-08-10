from auth import login, register
from database import create_tables, get_connection


def print_banner():
    print("\n" + "=" * 30)
    print("      Welcome to NoteApp")
    print("=" * 30)


def main_menu():
    while True:
        print("\nMain menu:")
        print("1. Register")
        print("2. Login")
        print("3. Exit")

        choice = input("Choose an option: ")

        if choice == "1":
            register()
        elif choice == "2":
            user = login()
            if user:
                note_menu(user)
        elif choice == "3":
            print("Goodbye!")
            break
        else:
            print("Invalid choice. Try again.")


def note_menu(user):
    while True:
        print(f"\nLogged in as: {user[1]}")
        print("1. Add note")
        print("2. View notes")
        print("3. Delete note")
        print("4. Logout")

        choice = input("Choose an option: ")

        if choice == "1":
            add_note(user[0])
        elif choice == "2":
            view_notes(user[0])
        elif choice == "3":
            delete_note(user[0])
        elif choice == "4":
            print("Logging out...")
            break
        else:
            print("Invalid choice. Try again.")


def add_note(user_id):
    print("\n===== ADD NOTE =====")
    title = input("Note title: ").strip()
    content = input("Note content: ").strip()

    if not title:
        print("Title cannot be empty.")
        return

    connection = get_connection()
    cursor = connection.cursor()

    query = "INSERT INTO notes (user_id, title, content) VALUES (%s, %s, %s)"

    try:
        cursor.execute(query, (user_id, title, content))
        connection.commit()
        print("Note added successfully! ✅")
    except Exception as e:
        print("Error:", e)
    finally:
        cursor.close()
        connection.close()


def view_notes(user_id):
    connection = get_connection()
    cursor = connection.cursor()

    query = "SELECT id, title, content, created_at FROM notes WHERE user_id = %s ORDER BY created_at DESC"
    cursor.execute(query, (user_id,))
    notes = cursor.fetchall()

    cursor.close()
    connection.close()

    if not notes:
        print("No notes found.")
        return

    print("\n===== YOUR NOTES =====")
    for note in notes:
        print(f"ID: {note[0]}")
        print(f"Title: {note[1]}")
        print(f"Created: {note[3]}")
        print(f"Content: {note[2]}")
        print("-" * 20)


def delete_note(user_id):
    note_id = input("Enter the ID of the note to delete: ").strip()

    if not note_id.isdigit():
        print("Please enter a valid note ID.")
        return

    connection = get_connection()
    cursor = connection.cursor()

    query = "DELETE FROM notes WHERE id = %s AND user_id = %s"
    cursor.execute(query, (note_id, user_id))
    connection.commit()

    if cursor.rowcount:
        print("Note deleted successfully.")
    else:
        print("Note not found or you are not allowed to delete it.")

    cursor.close()
    connection.close()


if __name__ == "__main__":
    create_tables()
    print_banner()
    main_menu()
