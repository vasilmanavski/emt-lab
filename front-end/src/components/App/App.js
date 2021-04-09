import './App.css';
import {BrowserRouter as Router, Redirect, Route} from 'react-router-dom';
import React, {Component} from "react";
import LibraryService from "../../repository/libraryRepository";
import Authors from "../Authors/authors";
import Countries from "../Countries/countries";
import Categories from "../Categories/categories";
import Books from "../Books/BookList/books"
import Header from "../Header/header";
import BookAdd from "../Books/BookAdd/bookAdd";
import BookEdit from "../Books/BookEdit/bookEdit";



class App extends Component{
  constructor(props) {
    super(props);
    this.state = {
      books: [],
      categories: [ "NOVEL",
          "THRILLER",
          "HISTORY",
          "FANTASY",
          "BIOGRAPHY",
          "CLASSICS",
          "DRAMA"
      ],
      countries: [],
      authors: [],
        selectedBook: {}

    }
  }

  render() {
    return (
        <Router>
          <Header />
          <main>
            <div className={"container"}>

              <Route path={"/countries"} exact render={() => <Countries countries={this.state.countries}/>}/>
              <Route path={"/authors"} exact render={() => <Authors authors={this.state.authors}/>}/>
                <Route path={"/categories"} exact render={() => <Categories categories={this.state.categories}/>}/>
                <Route path={"/books/add"} exact render={() =>
                    <BookAdd authors={this.state.authors}
                             categories={this.state.categories}
                                onAddBook={this.addBook}/>}/>
                     <Route path={"/books/edit/:id"} exact render={() =>
                        <BookEdit categories={this.state.categories}
                                     authors={this.state.authors}
                                     onEditBook={this.editBook}
                                     book={this.state.selectedBook}/>}/>
                     <Route path={["/books", "/"]} exact render={() =>
                         <Books books={this.state.books}
                              onDelete={this.deleteBook}
                              onEdit={this.getBook} onTaken={this.takenBook}/>}

                     />

              <Redirect to={"/books"}/>
            </div>
          </main>
        </Router>
    );
  }
  componentDidMount() {
    this.loadAuthors();
    this.loadCountries();
    this.loadBooks();
  }

  loadAuthors = () => {
    LibraryService.fetchAuthors()
        .then((data) => {
          this.setState({
            authors : data.data
          })
        });
  }

  loadBooks = () => {
    LibraryService.fetchBooks()
        .then((data) => {
          this.setState({
            books : data.data
          })
        });
  }

  loadCountries = () => {
    LibraryService.fetchCountries()
        .then((data) => {
          this.setState({
            countries : data.data
          })
        });
  }

    loadCategories = () => {
        LibraryService.fetchCategories()
            .then((data) => {
                this.setState({
                    categories : data.data
                })
            });
    }

    deleteBook = (id) => {
        LibraryService.deleteBook(id)
            .then(() => {
                this.loadBooks();
            });
    }

    addBook = (name, category, availableCopies, author) => {
        LibraryService.addBook(name, category, availableCopies, author)
            .then(() => {
                this.loadBooks();
            });
    }

    getBook = (id) => {
        LibraryService.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            })
    }

    editBook = (id, name, category, availableCopies, author) => {
        LibraryService.editBook(id, name, category, availableCopies, author)
            .then(() => {
                this.loadBooks();
            });
    }

    takenBook = (id) => {
        LibraryService.takenBook(id)
            .then(() => {
                this.loadBooks();
            });
    }


}

export default App;
