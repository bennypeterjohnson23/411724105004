from flask import Flask, render_template, redirect, url_for, request, session, flash
from database import (
    create_tables,
    create_user,
    get_user_by_credentials,
    get_user_by_username,
    get_notes,
    get_note_by_id,
    add_note,
    update_note,
    delete_note,
)

app = Flask(__name__)
app.secret_key = "secret-key-change-me"

create_tables()


def is_logged_in():
    return "user_id" in session


def current_user():
    return {
        "id": session.get("user_id"),
        "username": session.get("username"),
    }


@app.route("/")
def home():
    if is_logged_in():
        return redirect(url_for("notes_list"))
    return render_template("home.html")


@app.route("/register", methods=["GET", "POST"])
def register():
    if is_logged_in():
        return redirect(url_for("notes_list"))

    if request.method == "POST":
        username = request.form.get("username", "").strip()
        password = request.form.get("password", "").strip()

        if not username or not password:
            flash("Both username and password are required.", "error")
            return redirect(url_for("register"))

        if get_user_by_username(username):
            flash("This username is already taken.", "error")
            return redirect(url_for("register"))

        create_user(username, password)
        user = get_user_by_credentials(username, password)

        session["user_id"] = user["id"]
        session["username"] = user["username"]

        flash("Account created successfully!", "success")
        return redirect(url_for("notes_list"))

    return render_template("register.html")


@app.route("/login", methods=["GET", "POST"])
def login():
    if is_logged_in():
        return redirect(url_for("notes_list"))

    if request.method == "POST":
        username = request.form.get("username", "").strip()
        password = request.form.get("password", "").strip()

        user = get_user_by_credentials(username, password)

        if not user:
            flash("Invalid username or password.", "error")
            return redirect(url_for("login"))

        session["user_id"] = user["id"]
        session["username"] = user["username"]

        flash("Login successful!", "success")
        return redirect(url_for("notes_list"))

    return render_template("login.html")


@app.route("/logout")
def logout():
    session.clear()
    flash("You have been logged out.", "success")
    return redirect(url_for("home"))


@app.route("/notes")
def notes_list():
    if not is_logged_in():
        return redirect(url_for("login"))

    search = request.args.get("q", "").strip()
    notes = get_notes(session["user_id"], search)
    return render_template("notes.html", notes=notes, search=search)


@app.route("/notes/add", methods=["GET", "POST"])
def notes_add():
    if not is_logged_in():
        return redirect(url_for("login"))

    if request.method == "POST":
        title = request.form.get("title", "").strip()
        content = request.form.get("content", "").strip()

        if not title:
            flash("A title is required.", "error")
            return redirect(url_for("notes_add"))

        add_note(session["user_id"], title, content)
        flash("Note created successfully!", "success")
        return redirect(url_for("notes_list"))

    return render_template("note_form.html", action="Add", note=None)


@app.route("/notes/<int:note_id>/edit", methods=["GET", "POST"])
def notes_edit(note_id):
    if not is_logged_in():
        return redirect(url_for("login"))

    note = get_note_by_id(note_id, session["user_id"])

    if note is None:
        flash("Note not found.", "error")
        return redirect(url_for("notes_list"))

    if request.method == "POST":
        title = request.form.get("title", "").strip()
        content = request.form.get("content", "").strip()

        if not title:
            flash("A title is required.", "error")
            return redirect(url_for("notes_edit", note_id=note_id))

        update_note(note_id, session["user_id"], title, content)
        flash("Note updated successfully!", "success")
        return redirect(url_for("notes_list"))

    return render_template("note_form.html", action="Edit", note=note)


@app.route("/notes/<int:note_id>/delete", methods=["POST"])
def notes_delete(note_id):
    if not is_logged_in():
        return redirect(url_for("login"))

    delete_note(note_id, session["user_id"])
    flash("Note deleted successfully.", "success")
    return redirect(url_for("notes_list"))


if __name__ == "__main__":
    app.run(debug=True)
