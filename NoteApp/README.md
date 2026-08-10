# NoteApp

A simple Python + Flask note-taking web app backed by MySQL.

## What it does

- Register a new user
- Log in with username and password
- Create notes
- View your notes
- Edit notes
- Delete notes
- Uses MySQL for storage
- Includes a clean CSS-based web UI

## Files

- `app.py` — Flask application and web routes
- `database.py` — MySQL database connection and helper functions
- `auth.py` — login and registration helpers
- `main.py` — optional console-based version of the app
- `templates/` — HTML templates for the web interface
- `static/styles.css` — styles for the web UI

## Requirements

- Python 3.14+
- `mysql-connector-python`
- `Flask`
- MySQL server with a database named `note_app`

## Install

1. Activate your virtual environment:

```powershell
cd "c:\Users\benny\OneDrive\Desktop\CODES\.vscode\411724105004\411724105004"
.\.venv\Scripts\Activate.ps1
```

2. Install dependencies if needed:

```powershell
python -m pip install flask mysql-connector-python
```

## Run the web app

From the project root:

```powershell
cd "c:\Users\benny\OneDrive\Desktop\CODES\.vscode\411724105004\411724105004"
.\.venv\Scripts\python.exe "NoteApp\app.py"
```

Open `http://127.0.0.1:5000` in your browser.

## Notes

- The app creates tables automatically when started.
- If you want to use the console version instead, run `NoteApp\main.py`.
- If you need a feature like note search, categories, or password hashing, I can add it next.
