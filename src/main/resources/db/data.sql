insert into travels (id, about_us, contact, documentation, sustainability)
values (1, 'Welcome to our travel agency!', 'contact@travel.com', 'All documents are verified.',
        'We prioritize sustainability in tourism.');

INSERT INTO categories (id, day, day_tour, travel_id)
VALUES (1, 2025 - 01 - 01, 'Day tour example 1', 1),
       (2, 2025 - 02 - 01, 'Day tour example 2', 1),
       (3, 2025 - 03 - 01, 'Day tour example 3', 1),
       (4, 2025 - 04 - 01, 'Day tour example 4', 1),
       (5, 2025 - 05 - 01, 'Day tour example 5', 1);

INSERT INTO sights (id, latitude, longitude, description, name_of_sight, travel_id)
VALUES (1, 42.567, 74.352, 'Ala-Archa National Park', 'Ala-Archa', 1),
       (2, 41.841, 75.131, 'Son-Kul Lake', 'Son-Kul', 1),
       (3, 42.720, 75.891, 'Burana Tower', 'Burana Tower', 1),
       (4, 40.528, 72.805, 'Suleiman Mountain', 'Suleiman Mountain', 1),
       (5, 42.484, 78.395, 'Karakol Valley', 'Karakol', 1);

INSERT INTO about_kyrgyzstan (id,  description, name, video_file, sight_id)
VALUES (1, 'Kyrgyzstan is known for its beautiful mountains and cultural heritage.
The towering, glacier-covered mountains showcase the wonders of nature.
Issyk-Kul, the pearl of the lake, is famous worldwide.
Its ancient history and nomadic culture make it unique.
The melodious sound of the komuz reflects the soul of the people.
The yurt is a symbol of the Kyrgyz people''s national tradition.
Horse racing and kok-boru games glorify the spirit of our nation.
The delicious Kyrgyz cuisine captivates with its hospitality.
The warm-hearted people preserve the tradition of honoring guests.
Kyrgyzstan is a magnificent homeland where nature and history unite!', 'Explore Kyrgyzstan', 'intro.mp4',
        1),
       (2, 'Son-Kul Lake is a high-altitude summer pasture.
It is surrounded by rolling green meadows and majestic mountains.
The lake sits at an elevation of over 3,000 meters above sea level.
Nomadic herders bring their livestock here during the warm months.
Traditional yurts dot the landscape, preserving ancient ways of life.
The crystal-clear waters reflect the endless sky above.
Wild horses roam freely across the vast grasslands.
The serene beauty of Son-Kul makes it a perfect retreat for nature lovers.
Stunning sunrises and sunsets paint the sky with vibrant colors.
Son-Kul Lake is a true hidden gem of Kyrgyzstan’s natural wonders.', 'Son-Kul Lake', 'sonkul.mp4', 2),
       (3, 'Burana Tower is a historical site of the Silk Road.
It is located in the Chüy Valley of northern Kyrgyzstan.
The tower is a remnant of the ancient city of Balasagun.
It was originally built in the 10th or 11th century.
The tower once stood at 45 meters but now remains at 25 meters due to earthquakes.
Burana Tower served as a minaret for a grand mosque.
Surrounding the site are ancient petroglyphs and balbals (stone carvings).
It is an important archaeological and cultural heritage of Kyrgyzstan.
The site offers stunning views of the Tian Shan mountains.
Burana Tower is a fascinating glimpse into the region’s Silk Road history.', 'Burana Tower', 'burana.mp4', 3),
       (4, 'Suleiman Mountain is a sacred place in Kyrgyzstan.
It is located in the city of Osh, one of the oldest cities in Central Asia.
The mountain has been a pilgrimage site for centuries.
It is believed to have spiritual and healing properties.
Ancient petroglyphs and cave sites can be found on its slopes.
A small mosque built by Babur, the founder of the Mughal Empire, sits atop the mountain.
The site is recognized as a UNESCO World Heritage Site.
Thousands of visitors and pilgrims climb the mountain each year.
The panoramic view from the top offers breathtaking scenery of Osh.
Suleiman Mountain is a symbol of Kyrgyz history, spirituality, and culture.', 'Suleiman Mountain', 'suleiman.mp4', 4),
       (5, 'Karakol Valley is famous for its trekking routes.
It is located in the eastern part of Kyrgyzstan, near Issyk-Kul Lake.
The valley is surrounded by the stunning Terskey Alatau mountains.
It offers breathtaking landscapes with alpine meadows, rivers, and glaciers.
Trekkers can explore scenic trails leading to Ala-Kul Lake and other natural wonders.
The valley is home to diverse wildlife, including ibex and marmots.
During summer, nomads set up yurts, offering a glimpse into Kyrgyz traditions.
Karakol Valley is a gateway to adventure sports like horseback riding and skiing.
The crystal-clear rivers make it a perfect spot for camping and photography.
It is one of the best destinations for nature lovers and outdoor enthusiasts.', 'Karakol Valley', 'karakol.mp4', 5);

INSERT INTO users (id, email, name, password, phone_number, role, travel_id)
VALUES (1, 'asan@gmail.com', 'Asan', 'Asan1234', '+996555123456', 'ROLE_ADMIN', 1);
ALTER TABLE users DROP CONSTRAINT users_travel_id_key;

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
       (1, 'tour1_img2.jpg'),
       (2, 'tour2_img1.jpg');


