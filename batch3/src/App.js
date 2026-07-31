import { BrowserRouter, Link, Route, Routes } from "react-router-dom";
import "./App.css";

const books = [
  {
    slug: "atomic-habits",
    title: "Atomic Habits",
    author: "James Clear",
    genre: "Self-Help",
    description:
      "A practical guide to building good habits and breaking bad ones through small, consistent steps.",
  },
  {
    slug: "the-alchemist",
    title: "The Alchemist",
    author: "Paulo Coelho",
    genre: "Adventure",
    description:
      "A timeless story about following your dreams and listening to your heart.",
  },
  {
    slug: "1984",
    title: "1984",
    author: "George Orwell",
    genre: "Dystopian",
    description:
      "A powerful novel about truth, power, and the danger of total control.",
  },
  {
    slug: "to-kill-a-mockingbird",
    title: "To Kill a Mockingbird",
    author: "Harper Lee",
    genre: "Classic",
    description:
      "A moving story about justice, empathy, and growing up in a divided community.",
  },
  {
    slug: "the-hobbit",
    title: "The Hobbit",
    author: "J.R.R. Tolkien",
    genre: "Fantasy",
    description:
      "An exciting adventure about a brave hobbit who travels through magical lands.",
  },
  {
    slug: "pride-and-prejudice",
    title: "Pride and Prejudice",
    author: "Jane Austen",
    genre: "Romance",
    description:
      "A witty and elegant story about love, manners, and first impressions.",
  },
];

const authors = [
  {
    slug: "james-clear",
    name: "James Clear",
    famousBooks: ["Atomic Habits"],
    country: "United States",
    biography:
      "James Clear is a writer and speaker who teaches habits and personal improvement.",
  },
  {
    slug: "paulo-coelho",
    name: "Paulo Coelho",
    famousBooks: ["The Alchemist", "Brida"],
    country: "Brazil",
    biography:
      "Paulo Coelho is a famous Brazilian author known for inspiring and spiritual stories.",
  },
  {
    slug: "george-orwell",
    name: "George Orwell",
    famousBooks: ["1984", "Animal Farm"],
    country: "United Kingdom",
    biography:
      "George Orwell was a British writer famous for political and social commentary.",
  },
];

function HomePage() {
  return (
    <div className="page">
      <h1>Welcome to the Online Book Store</h1>
      <p>Explore your next favorite book, discover amazing authors, and learn more about our store.</p>
      <div className="card-grid">
        <Link className="card" to="/books">Books</Link>
        <Link className="card" to="/authors">Authors</Link>
        <Link className="card" to="/about">About</Link>
      </div>
    </div>
  );
}

function BooksPage() {
  return (
    <div className="page">
      <h1>Books</h1>
      <p>Choose a book to read more about it.</p>
      <ul className="list">
        {books.map((book) => (
          <li key={book.slug}>
            <Link to={`/books/${book.slug}`}>{book.title}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

function BookPage({ book, books, index }) {
  const previousBook = index > 0 ? books[index - 1] : null;
  const nextBook = index < books.length - 1 ? books[index + 1] : null;

  return (
    <div className="page">
      <h1>{book.title}</h1>
      <p>
        <strong>Author:</strong> {book.author}
      </p>
      <p>
        <strong>Genre:</strong> {book.genre}
      </p>
      <p>{book.description}</p>
      <div className="nav-links">
        <Link to="/books">Back to Books</Link>
        {previousBook ? (
          <Link to={`/books/${previousBook.slug}`}>Previous</Link>
        ) : null}
        {nextBook ? <Link to={`/books/${nextBook.slug}`}>Next</Link> : null}
      </div>
    </div>
  );
}

function AuthorsPage() {
  return (
    <div className="page">
      <h1>Authors</h1>
      <p>Meet some of the writers featured in our store.</p>
      <ul className="list">
        {authors.map((author) => (
          <li key={author.slug}>
            <Link to={`/authors/${author.slug}`}>{author.name}</Link>
          </li>
        ))}
      </ul>
    </div>
  );
}

function AuthorPage({ author }) {
  return (
    <div className="page">
      <h1>{author.name}</h1>
      <p>
        <strong>Famous Books:</strong> {author.famousBooks.join(", ")}
      </p>
      <p>
        <strong>Country:</strong> {author.country}
      </p>
      <p>{author.biography}</p>
      <Link to="/authors">Back to Authors</Link>
    </div>
  );
}

function AboutPage() {
  return (
    <div className="page">
      <h1>About Our Book Store</h1>
      <p>
        Our bookstore is a friendly place for readers of all ages. We love sharing stories,
        knowledge, and inspiration with our community.
      </p>
      <div className="nav-links">
        <Link to="/">Return Home</Link>
        <Link to="/books">Visit Books</Link>
      </div>
    </div>
  );
}

function NotFoundPage() {
  return (
    <div className="page">
      <h1>404 - Page Not Found</h1>
      <p>The page you are looking for does not exist.</p>
      <Link to="/">Return Home</Link>
    </div>
  );
}

function App() {
  return (
    <BrowserRouter>
      <div className="app">
        <nav className="nav">
          <Link to="/">Home</Link>
          <Link to="/books">Books</Link>
          <Link to="/authors">Authors</Link>
          <Link to="/about">About</Link>
        </nav>

        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/books" element={<BooksPage />} />
          {books.map((book, index) => (
            <Route
              key={book.slug}
              path={`/books/${book.slug}`}
              element={<BookPage book={book} books={books} index={index} />}
            />
          ))}
          <Route path="/authors" element={<AuthorsPage />} />
          {authors.map((author) => (
            <Route
              key={author.slug}
              path={`/authors/${author.slug}`}
              element={<AuthorPage author={author} />}
            />
          ))}
          <Route path="/about" element={<AboutPage />} />
          <Route path="*" element={<NotFoundPage />} />
        </Routes>
      </div>
    </BrowserRouter>
  );
}

export default App;