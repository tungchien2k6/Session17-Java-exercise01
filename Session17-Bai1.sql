-- Tạo bảng
CREATE TABLE movies (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    director VARCHAR(255),
    year INT
);

-- 1. Stored Procedure: Thêm phim
CREATE OR REPLACE PROCEDURE add_movie(
    p_title VARCHAR, 
    p_director VARCHAR, 
    p_year INT
)
LANGUAGE plpgsql AS $$
BEGIN
    INSERT INTO movies(title, director, year) 
    VALUES (p_title, p_director, p_year);
    COMMIT;
END;
$$;

-- 2. Stored Procedure: Liệt kê phim
CREATE OR REPLACE PROCEDURE list_movies()
LANGUAGE plpgsql AS $$
DECLARE
    rec RECORD;
BEGIN
    FOR rec IN 
        SELECT * FROM movies ORDER BY id 
    LOOP
        RAISE NOTICE 'ID: % | Title: % | Director: % | Year: %', 
                     rec.id, rec.title, rec.director, rec.year;
    END LOOP;
END;
$$;

-- 3. Stored Procedure: Sửa phim
CREATE OR REPLACE PROCEDURE update_movie(
    p_id INT, 
    p_title VARCHAR, 
    p_director VARCHAR, 
    p_year INT
)
LANGUAGE plpgsql AS $$
BEGIN
    UPDATE movies 
    SET title = p_title, 
        director = p_director, 
        year = p_year 
    WHERE id = p_id;
    COMMIT;
END;
$$;

-- 4. Stored Procedure: Xóa phim
CREATE OR REPLACE PROCEDURE delete_movie(p_id INT)
LANGUAGE plpgsql AS $$
BEGIN
    DELETE FROM movies WHERE id = p_id;
    COMMIT;
END;
$$;