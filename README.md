# bookstore

# Get all books : GET : http://localhost:8080/api/v1/books

# Get book based on Id : GET : http://localhost:8080/api/v1/books/1

# Post a book : POST : http://localhost:8080/api/v1/books
{
    "category": "cooking",
    "title": "Awesome Chowmein",
    "language": "en",
    "year": "2005",
    "price": 25.0,
    "authors": [
        {
            "name": "Sam T. Bruce"
        },
        {
            "name": "Giada De Laurentiis"
        }
    ]
}

# update a book : PUT : http://localhost:8080/api/v1/books/4
{
    "category": "cooking",
    "title": "Awesome Chowmein",
    "language": "en",
    "year": "2005",
    "price": 50.0,
    "authors": [
        {
            "name": "Sam T. Bruce"
        },
        {
            "name": "Giada De Laurentiis"
        }
    ]
}

# Delete a book : DELETE :http://localhost:8080/api/v1/books/4

# Get all authors : GET : http://localhost:8080/api/v1/authors

# Get author based on Id : GET : http://localhost:8080/api/v1/authors/1

# Post a author : POST : http://localhost:8080/api/v1/authors
 {
    "name": "Hiiren"
 }

# update and author : PUT : http://localhost:8080/api/v1/authors/5
 {
    "name": "Hiiren Parmar"
 }

# delete an author : DELETE : http://localhost:8080/api/v1/authors/5

