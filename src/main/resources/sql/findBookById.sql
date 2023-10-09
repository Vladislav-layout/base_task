SELECT b.*
FROM books b
LEFT JOIN bookshelves bs ON b.shelf_id = bs.shelf_id
WHERE b.book_id = :id