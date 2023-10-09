UPDATE books
SET first_name = :firstName,
    last_name = :lastName,
    book_name = :bookName,
    book_count = :bookCount,
    shelf_id = :shelfId
WHERE book_id = :bookId
