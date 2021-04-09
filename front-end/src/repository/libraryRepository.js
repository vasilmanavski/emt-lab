import React from 'react';
import axios from "../custom-axios/axios";

const LibraryService = {
    fetchAuthors: () => {
        return axios.get("/authors");
    },
    fetchBooks: () => {
        return axios.get("/books");
    },
    fetchCountries: () => {
        return axios.get("/countries");
    },
    deleteBook: (id) => {
        return axios.delete(`/books/delete/${id}`);
    },
    addBook: (name, category, availableCopies, author) => {
        return axios.post("/books/add", {
            "name": name,
            "category": category,
            "availableCopies": availableCopies,
            "author": author
        });
    },
    editBook: (id, name, category, availableCopies, author) => {
        return axios.put(`/books/edit/${id}`, {
            "name": name,
            "category": category,
            "availableCopies": availableCopies,
            "author": author

        });
    },
    getBook: (id) => {
        return axios.get(`/books/${id}`);
    },

    takenBook: (id) => {
        return axios.post(`/books/taken/${id}`);
    }
}

export default LibraryService;