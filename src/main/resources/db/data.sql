insert into travels (id, about_us, contact, documentation, sustainability)
values (1, 'Welcome to our travel agency!', 'contact@travel.com', 'All documents are verified.',
        'We prioritize sustainability in tourism.'),
       (2, 'Welcome to our travel agency!', 'contact@travel.com', 'All documents are verified.',
        'We prioritize sustainability in tourism.');

INSERT INTO categories (id, day, day_tour, travel_id, image)
VALUES (1, 1, 'Day tour example 1', 1, 'image'),
       (2, 2, 'Day tour example 2', 1, 'image'),
       (3, 3, 'Day tour example 3', 1, 'image'),
       (4, 4, 'Day tour example 4', 1, 'image'),
       (5, 5, 'Day tour example 5', 1, 'image');
INSERT INTO sights (id, latitude, longitude, description, name_of_sight, travel_id, title_from_video)
VALUES (1, 42.567, 74.352, 'Ala-Archa National Park', 'Ala-Archa', 1, 'Ala-Archa National Park Video'),
       (2, 41.841, 75.131, 'Son-Kul Lake', 'Son-Kul', 1, 'Son-Kul Lake Video'),
       (3, 42.720, 75.891, 'Burana Tower', 'Burana Tower', 1, 'Burana Tower Video'),
       (4, 40.528, 72.805, 'Suleiman Mountain', 'Suleiman Mountain', 1, 'Suleiman Mountain Video'),
       (5, 42.484, 78.395, 'Karakol Valley', 'Karakol', 1, 'Karakol Valley Video');

INSERT INTO about_kyrgyzstan (id, name, video_file, type,description)
VALUES (1, 'Explore Kyrgyzstan', 'intro.mp4', 'CULTURE','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
     Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
     Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
     Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
     Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
     Кийиз үй – кыргыз элинин улуттук салтынын символу.
     Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
     Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
     Кең пейил эли конокту урматтоо салтын сактап келет.
     Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!'),
       (2, 'Son - Kul Lake', 'sonkul.mp4', 'TRADITIONAL','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
     Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
     Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
     Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
     Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
     Кийиз үй – кыргыз элинин улуттук салтынын символу.
     Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
     Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
     Кең пейил эли конокту урматтоо салтын сактап келет.
     Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!'),
       (3, 'Burana Tower', 'burana.mp4', 'HISTORICAL_PLACES','Кыргызстан өзүнүн кооз тоолору жана маданий мурасы менен белгилүү.
     Асман тиреген мөңгүлүү тоолор жаратылыштын кереметин тартуулайт.
     Ысык-Көл, көлдүн бермети, дүйнө жүзүндө таанымал.
     Анын байыркы тарыхы жана көчмөн маданияты аны өзгөчөлөндүрөт.
     Комуздун мукам үнү элдин жан дүйнөсүн чагылдырат.
     Кийиз үй – кыргыз элинин улуттук салтынын символу.
     Ат чабыш жана көк бөрү оюндары элибиздин рухун даңазалайт.
     Даамдуу кыргыз ашканасы өзүнүн меймандостугу менен суктандырат.
     Кең пейил эли конокту урматтоо салтын сактап келет.
     Кыргызстан – жаратылыш менен тарых бириккен керемет мекен!');

INSERT INTO users (id, email, name, password, phone_number, role, travel_id)
VALUES (1, 'asan@gmail.com', 'Asan', '$2a$12$dyHbyL5DkJFtoGTgk0jaouZAhpmu6mfZOg7tgrXCae2VmHOIsVvqS', '+996555123456', 'ROLE_ADMIN', 1),
       (2, 'bekbolot@gmail.com', 'Bekbolot', '$2y$10$4Y4xzBVSR5n3PcbCg9sjbur0eVXCC6U8ehHbhH.G/Yizg4oILmi0u', '+996554450820', 'ROLE_ADMIN', 2);

INSERT INTO tours (id, latitude, longitude, about_tour, date_from, date_to, days, pax, nights, price, tour_name,
                   category_id, sight_id, travel_id)
VALUES (1, 42.567, 74.352, 'Ala-Archa Hiking Tour', '2025-01-01', '2025-01-03', 3, 15, 2, 100.0, 'Ala-Archa Tour', 1, 1,
        1),
       (2, 41.841, 75.131, 'Son-Kul Lake Experience', '2025-02-01', '2025-02-05', 5, 20, 4, 200.0, 'Son-Kul Adventure',
        2, 2, 1),
       (3, 42.720, 75.891, 'Discover Burana Tower', '2025-03-01', '2025-03-03', 3, 10, 2, 80.0, 'Burana Tower Tour', 3,
        3, 1),
       (4, 40.528, 72.805, 'Sacred Suleiman Mountain', '2025-04-01', '2025-04-02', 2, 15, 1, 50.0,
        'Suleiman Mountain Visit', 4, 4, 1),
       (5, 42.484, 78.395, 'Karakol Nature Retreat', '2025-05-01', '2025-05-06', 6, 12, 5, 300.0, 'Karakol Nature Tour',
        5, 5, 1);

insert into about_kyrgyzstan_images (about_kyrgyzstan_id, images)
VALUES (1, 'image1.jpg'),
       (1, 'image2.jpg'),
       (1, 'image3.jpg');


INSERT INTO sight_images (sight_id, images)
VALUES (1, 'sight1.jpg'),
       (2, 'sight2.jpg'),
       (3, 'sight3.jpg');

INSERT INTO tour_details_of_tour (tour_id, details_of_tour, details_of_tour_key)
VALUES (1, 'Comfortable accommodation', 'accommodation'),
       (1, 'Meals included', 'meals'),
       (1, 'Experienced guide', 'guide');

INSERT INTO tour_images (tour_id, images)
VALUES (1, 'tour1_img1.jpg'),
       (2, 'tour2_img2.jpg'),
       (3, 'tour3_img1.jpg'),
       (4, 'tour4_img1.jpg'),
       (5, 'tour5_img1.jpg');


