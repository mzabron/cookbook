CREATE TABLE IF NOT EXISTS recipes (
                                       id INTEGER PRIMARY KEY AUTOINCREMENT,
                                       title TEXT NOT NULL,
                                       ingredients TEXT NOT NULL,
                                       instructions TEXT NOT NULL
);

INSERT INTO recipes (title, ingredients, instructions) VALUES
('Spaghetti Aglio e Olio', 'Składniki: 200g makaronu spaghetti, 4 ząbki czosnku, 50ml oliwy z oliwek, świeża bazylia, parmezan (opcjonalnie)',
 '1. Ugotuj makaron al dente w osolonej wodzie zgodnie z instrukcją na opakowaniu. 2. W międzyczasie obierz czosnek i pokrój go w cienkie plasterki. 3. Na dużej patelni rozgrzej oliwę z oliwek, dodaj czosnek i smaż na średnim ogniu przez około 2-3 minuty, aż czosnek stanie się złocisty, ale nie przypalony. 4. Dodaj ugotowany makaron na patelnię z czosnkiem i oliwą, wymieszaj dokładnie. 5. Na koniec posyp świeżo posiekaną bazylią oraz parmezanem, jeżeli lubisz.')
,('Pomidorowa zupa', 'Składniki: 1 kg pomidorów, 1 cebula, 2 ząbki czosnku, 1 litr bulionu warzywnego, 2 łyżki oliwy z oliwek, 1 łyżeczka soli, 1/2 łyżeczki pieprzu, świeża bazylia do podania',
'1. Pokrój cebulę i czosnek, a następnie podsmaż je na oliwie w dużym garnku przez około 5 minut, aż będą miękkie. 2. Dodaj pomidory (świeże lub z puszki) i zalej wodą lub bulionem. Gotuj przez 20 minut, aż pomidory będą miękkie. 3. Zmiksuj zupę na krem za pomocą blendera. 4. Dodaj świeżą bazylię, sól i pieprz do smaku. Gotuj jeszcze przez kilka minut. 5. Podawaj zupę z łyżką śmietany, posypaną bazylią.')
,('Kurczak pieczony z ziemniakami', 'Składniki: 4 udka kurczaka, 6 ziemniaków, 2 łyżki oliwy z oliwek, 1 łyżeczka papryki, 1 łyżeczka soli, 1/2 łyżeczki pieprzu, 2 ząbki czosnku, 1 gałązka rozmarynu',
'1. Umyj i pokrój ziemniaki na kawałki. 2. Kurczaka natrzyj przyprawami (np. solą, pieprzem, papryką) i czosnkiem. 3. Na blasze wyłóż ziemniaki, a obok umieść kurczaka. 4. Piecz w temperaturze 200°C przez około 45-60 minut, aż kurczak będzie złocisty, a ziemniaki miękkie.')
,('Sałatka grecka', 'Składniki: 2 pomidory, 1 ogórek, 1 czerwona papryka, 1 cebula, 200g sera feta, 100g czarnych oliwek, 4 łyżki oliwy z oliwek, 1 łyżeczka oregano, sól, pieprz do smaku',
'1. Pokrój pomidory, ogórki, paprykę oraz cebulę na kawałki. 2. Pokrusz ser feta na kawałki i dodaj do warzyw. 3. Polej sałatkę oliwą z oliwek, dodaj świeżo mielony pieprz, sól i oregano. 4. Wymieszaj wszystkie składniki. 5. Na koniec, jeśli lubisz, dodaj czarne oliwki.')
,('Bolognese', 'Składniki: 400g mielonego mięsa (wołowina lub wieprzowina), 1 cebula, 2 ząbki czosnku, 2 pomidory (świeże lub z puszki), 1/2 szklanki przecieru pomidorowego, 1/2 łyżeczki soli, 1/2 łyżeczki pieprzu, 1 łyżeczka oregano, 300g makaronu spaghetti',
'1. Na dużej patelni rozgrzej oliwę z oliwek, dodaj posiekaną cebulę i czosnek. Smaż przez kilka minut, aż staną się miękkie. 2. Dodaj mielone mięso i smaż, aż się zrumieni. 3. Dodaj pokrojone pomidory i przecier pomidorowy. Gotuj na małym ogniu przez około 30 minut, aż sos zgęstnieje. 4. Ugotuj makaron al dente, a następnie wymieszaj z sosem. 5. Podawaj gorące, posypane świeżą bazylią i parmezanem.')
,('Zupa krem z brokułów', 'Składniki: 500g brokułów, 1 cebula, 2 ząbki czosnku, 500ml bulionu warzywnego, 200ml śmietany 30%, 2 łyżki oliwy z oliwek, sól, pieprz do smaku',
'1. W garnku rozgrzej oliwę i podsmaż cebulę oraz czosnek przez około 5 minut. 2. Dodaj podzielone na różyczki brokuły i zalej bulionem. Gotuj przez 15-20 minut, aż brokuły staną się miękkie. 3. Zmiksuj zupę na krem za pomocą blendera. 4. Dodaj śmietanę, sól i pieprz do smaku. 5. Gotuj przez kilka minut, a następnie podawaj.')
,('Zupa ogórkowa', 'Składniki: 500g ogórków kiszonych, 1 cebula, 2 ziemniaki, 1 litr bulionu warzywnego, 200ml śmietany 18%, 1 łyżeczka soli, 1/2 łyżeczki pieprzu, koperek do podania',
'1. Obierz ziemniaki i pokrój je w kostkę, a cebulę drobno posiekaj. 2. Ogórki kiszone zetrzyj na tarce. 3. W garnku gotuj ziemniaki z cebulą i bulionem przez 15 minut. 4. Dodaj starty ogórek kiszony i gotuj przez kolejne 10 minut. 5. Na koniec dodaj śmietanę, sól, pieprz oraz świeży koperek. Gotuj przez 5 minut. 6. Podawaj gorące z koperkiem na wierzchu.')
,('Sałatka ziemniaczana', 'Składniki: 1 kg ziemniaków, 3 jajka, 1 cebula, 3 ogórki konserwowe, 200g majonezu, 1 łyżeczka musztardy, sól, pieprz do smaku',
'1. Ugotuj ziemniaki w mundurkach i jajka na twardo. 2. Pokrój cebulę, ogórki i ziemniaki w kostkę. 3. Obierz jajka i posiekaj je. 4. W misce wymieszaj wszystkie składniki. 5. Dodaj majonez, musztardę, sól i pieprz do smaku. 6. Wymieszaj i podawaj schłodzoną.')
,('Chili con carne', 'Składniki: 500g mielonego mięsa wołowego, 1 cebula, 2 ząbki czosnku, 1 czerwona papryka, 1 puszka pomidorów krojonych, 1 puszka czerwonej fasoli, 1 łyżeczka kuminu, 1 łyżeczka papryki w proszku, 1/2 łyżeczki chili, sól, pieprz do smaku',
'1. Na patelni rozgrzej oliwę, podsmaż pokrojoną cebulę i czosnek. 2. Dodaj mielone mięso i smaż, aż się zrumieni. 3. Dodaj pokrojoną paprykę, pomidory i przyprawy. Gotuj na średnim ogniu przez 15 minut. 4. Dodaj fasolę, gotuj jeszcze przez 10 minut, aż danie się zagęści. 5. Podawaj z ryżem lub chlebem.')
,('Makaron z tuńczykiem', 'Składniki: 200g makaronu, 1 puszka tuńczyka w oliwie, 1 ząbek czosnku, 1/2 cebuli, 2 łyżki oliwy z oliwek, 1 łyżeczka soku z cytryny, sól, pieprz do smaku',
'1. Ugotuj makaron al dente zgodnie z instrukcją na opakowaniu. 2. W międzyczasie na patelni rozgrzej oliwę i podsmaż pokrojoną cebulę oraz czosnek. 3. Dodaj odsączonego tuńczyka z puszki, sok z cytryny, sól i pieprz. 4. Wymieszaj ugotowany makaron z tuńczykiem i podawaj od razu.')
,('Pstrąg pieczony w folii', 'Składniki: 4 filety z pstrąga, 1 cytryna, 1 łyżka masła, sól, pieprz, świeża bazylia',
'1. Rozgrzej piekarnik do 180°C. 2. Na każdym filecie pstrąga połóż kawałek masła, dopraw solą, pieprzem i świeżyą bazylią. 3. Skrop sokiem z cytryny. 4. Zawiń rybę w folię aluminiową i piecz przez 20 minut. 5. Podawaj z ulubionymi dodatkami.')
,('Kotlety mielone', 'Składniki: 500g mielonego mięsa wieprzowego, 1 jajko, 1 cebula, 2 ząbki czosnku, 1/2 szklanki bułki tartej, sól, pieprz, olej do smażenia',
'1. Posiekaj cebulę i czosnek, następnie podsmaż je na patelni. 2. W misce wymieszaj mięso mielone, jajko, bułkę tartą, cebulę, czosnek oraz przyprawy. 3. Formuj kotlety i smaż na rozgrzanym oleju przez około 5-7 minut z każdej strony. 4. Podawaj z ziemniakami lub sałatką.')
,('Naleśniki z dżemem', 'Składniki: 200g mąki, 2 jajka, 300ml mleka, 1 łyżka oleju, 1 łyżka cukru, dżem do smaku',
'1. W misce wymieszaj mąkę, jajka, mleko, olej i cukier. 2. Rozgrzej patelnię i smaż cienkie naleśniki. 3. Po usmażeniu, posmaruj naleśniki dżemem. 4. Zwiń je w rulon i podawaj gorące.')
,('Fasolka po bretońsku', 'Składniki: 500g fasoli, 1 cebula, 2 ząbki czosnku, 300g kiełbasy, 2 puszki pomidorów, 1 łyżeczka papryki w proszku, 1 łyżeczka oregano, sól, pieprz do smaku',
'1. Ugotuj fasolę zgodnie z instrukcją na opakowaniu. 2. Na patelni podsmaż pokrojoną cebulę, czosnek oraz kiełbasę. 3. Dodaj pomidory i przyprawy. 4. Dodaj ugotowaną fasolę do sosu, gotuj przez 10 minut. 5. Podawaj gorące z kawałkiem chleba.')
,('Tatar wołowy', 'Składniki: 300g mięsa wołowego (najlepiej polędwica), 1 cebula, 1 żółtko, 1 łyżeczka musztardy, sól, pieprz, ogórek konserwowy',
'1. Mięso zmiel w maszynce do mięsa lub posiekaj bardzo drobno. 2. Posiekaj cebulę i ogórek. 3. Wymieszaj mięso z cebulą, żółtkiem, musztardą oraz przyprawami. 4. Podawaj tatar z kromką chleba lub frytkami.')
,('Zapiekanka ziemniaczana', 'Składniki: 6 ziemniaków, 200g sera żółtego, 1 cebula, 1/2 szklanki śmietany, 1 łyżeczka soli, 1/2 łyżeczki pieprzu',
'1. Obierz ziemniaki i pokrój je w plastry. 2. Na dnie naczynia żaroodpornego ułóż warstwę ziemniaków, następnie posyp cebulą i serem. 3. Powtórz warstwy, aż skończą się składniki. 4. Na wierzch wylej śmietanę i zapiecz przez 30 minut w piekarniku w temperaturze 180°C. 5. Podawaj gorące jako danie główne lub dodatek.')
,('Curry z kurczakiem', 'Składniki: 500g piersi z kurczaka, 1 cebula, 2 ząbki czosnku, 200ml mleka kokosowego, 1 łyżeczka curry, 1/2 łyżeczki kurkumy, 1/2 łyżeczki papryki, sól, pieprz',
'1. Pokrój kurczaka w kawałki i podsmaż go na oliwie w dużej patelni. 2. Dodaj pokrojoną cebulę i czosnek, smaż przez 3-4 minuty. 3. Dodaj przyprawy i mleko kokosowe, gotuj na małym ogniu przez 20 minut. 4. Podawaj z ryżem lub chlebkiem naan.')
,('Pizza Margherita', 'Składniki: 250g ciasta na pizzę, 200g sosu pomidorowego, 200g mozzarelli, świeża bazylia, 1 łyżeczka oliwy z oliwek, sól, pieprz',
'1. Rozgrzej piekarnik do 220°C. 2. Na przygotowanym cieście rozprowadź sos pomidorowy. 3. Na sosie ułóż plastry mozzarelli. 4. Skrop oliwą, posyp solą i pieprzem. 5. Piecz pizzę przez 12-15 minut. 6. Po upieczeniu udekoruj świeżą bazylią.')
,('Jajka w majonezie', 'Składniki: 6 jajek, 100g majonezu, 1 łyżeczka musztardy, sól, pieprz do smaku',
'1. Ugotuj jajka na twardo (około 10 minut). 2. Obierz jajka i przekaż do miski. 3. Dodaj majonez, musztardę, sól i pieprz. 4. Wymieszaj dokładnie. 5. Podawaj jako przekąskę lub dodatek.')
,('Kluski śląskie', 'Składniki: 1 kg ziemniaków, 1 jajko, 200g mąki ziemniaczanej, 1 łyżeczka soli',
'1. Ugotuj ziemniaki w mundurkach, następnie je obierz i przeciśnij przez praskę. 2. Wymieszaj ziemniaki z mąką ziemniaczaną, jajkiem i solą. 3. Uformuj kulki z ciasta, a w każdej zrób wgłębienie palcem. 4. Gotuj kluski w osolonej wodzie przez 4-5 minut, aż wypłyną na powierzchnię. 5. Podawaj z sosem mięsnym.')
,('Sushi', 'Składniki: 200g ryżu do sushi, 1 łyżka octu ryżowego, 1 łyżeczka cukru, 1 łyżeczka soli, 10 listków nori, 200g łososia, 1 ogórek, wasabi, sos sojowy',
'1. Ugotuj ryż do sushi zgodnie z instrukcjami na opakowaniu. 2. Wymieszaj ocet ryżowy z cukrem i solą, a następnie wlej do ugotowanego ryżu. 3. Na macie bambusowej ułóż arkusz nori, na nim cienką warstwę ryżu. 4. Na ryżu ułóż pokrojony łosoś i ogórek, zroluj sushi. 5. Pokrój na kawałki i podawaj z wasabi oraz sosem sojowym.')
,('Chleb domowy', 'Składniki: 500g mąki pszennej, 25g drożdży, 1 łyżeczka cukru, 1 łyżeczka soli, 300ml ciepłej wody, 2 łyżki oliwy z oliwek',
'1. Rozpuść drożdże w ciepłej wodzie z cukrem, odstaw na 10 minut, aż zacznie pracować. 2. W misce wymieszaj mąkę, sól, dodaj oliwę i drożdże. 3. Zagnieć ciasto, przykryj i odstaw na 1 godzinę do wyrośnięcia. 4. Uformuj bochenek, odstaw do wyrośnięcia na 30 minut. 5. Piecz w nagrzanym piekarniku przez 30-35 minut w temperaturze 180°C.')
,('Zupa krem z pomidorów', 'Składniki: 800g pomidorów, 1 cebula, 2 ząbki czosnku, 500ml bulionu warzywnego, 200ml śmietany 18%, sól, pieprz',
'1. Podsmaż cebulę i czosnek w garnku. 2. Dodaj pokrojone pomidory i gotuj przez 5 minut. 3. Dodaj bulion, sól i pieprz, gotuj przez kolejne 10 minut. 4. Zblenduj zupę na gładki krem, dodaj śmietanę i gotuj przez 5 minut. 5. Podawaj gorącą z ziołami.')
,('Risotto z kurczakiem', 'Składniki: 300g piersi z kurczaka, 250g ryżu arborio, 1 cebula, 500ml bulionu, 50g parmezanu, 1/2 szklanki białego wina, sól, pieprz',
'1. Pokrój kurczaka w kostkę i podsmaż na patelni. 2. Dodaj pokrojoną cebulę i smaż do zeszklenia. 3. Dodaj ryż i smaż przez 2 minuty. 4. Dolewaj bulion stopniowo, mieszając, aż ryż wchłonie płyn. 5. Na koniec dodaj parmezan, sól, pieprz. Podawaj gorące.')
,('Grzanki czosnkowe', 'Składniki: 1 bagietka, 3 ząbki czosnku, 50g masła, 1 łyżeczka soli, świeża pietruszka',
'1. Bagietkę pokrój na plastry i lekko podpiecz w piekarniku. 2. W misce wymieszaj masło z przeciśniętym czosnkiem, solą i posiekaną pietruszką. 3. Posmaruj grzanki przygotowanym masłem. 4. Włóż grzanki do piekarnika na 5 minut, aż staną się chrupiące.')
,('Gofry', 'Składniki: 250g mąki, 2 jajka, 300ml mleka, 1 łyżka cukru, 1 łyżeczka proszku do pieczenia, 1 łyżka oleju',
'1. W misce wymieszaj suche składniki: mąkę, cukier, proszek do pieczenia. 2. Dodaj jajka, mleko i olej, wymieszaj na gładkie ciasto. 3. Rozgrzej gofrownicę i wylej ciasto. 4. Piecz przez około 5-7 minut, aż gofry będą złociste. 5. Podawaj z owocami i bitą śmietaną.')
,('Chłodnik litewski', 'Składniki: 2 buraki, 1 litr kefiru, 1 ogórek, 3 jajka, 1 łyżka koperku, sól, pieprz do smaku',
'1. Ugotuj buraki, a następnie je obierz i zetrzyj na tarce. 2. Ogórek pokrój w kostkę. 3. W misce wymieszaj kefir, buraki, ogórek, posiekane jajka i koperek. 4. Dopraw solą i pieprzem. 5. Schłodź w lodówce przez godzinę przed podaniem.')
,('Babka wielkanocna', 'Składniki: 500g mąki, 250g masła, 200g cukru, 4 jajka, 1 łyżeczka proszku do pieczenia, 1 łyżeczka ekstraktu waniliowego, 100g rodzynek',
'1. Rozgrzej piekarnik do 180°C. 2. Utrzyj masło z cukrem na puszystą masę, dodaj jajka, a następnie mąkę wymieszaną z proszkiem do pieczenia. 3. Dodaj ekstrakt waniliowy i rodzynki. 4. Wlej ciasto do formy i piecz przez 40-45 minut. 5. Po upieczeniu ostudź.')
,('Śledzie w oleju', 'Składniki: 300g śledzi, 1 cebula, 100ml oleju, 1 łyżka octu, 1 łyżeczka cukru, przyprawy: pieprz, ziele angielskie, liść laurowy',
'1. Śledzie namocz przez kilka godzin w zimnej wodzie. 2. Pokrój cebulę w cienkie plastry, a następnie podsmaż ją na oleju. 3. Do oleju dodaj ocet, cukier, przyprawy. 4. Włóż śledzie do słoika, zalej marynatą i odstaw na kilka dni.')
,('Kaczka pieczona', 'Składniki: 1 kaczka, 2 jabłka, 2 ząbki czosnku, 1 łyżeczka tymianku, 1 łyżka miodu, sól, pieprz',
'1. Umyj kaczkę, osusz i natrzyj solą, pieprzem i tymiankiem. 2. Jabłka pokrój na ćwiartki, czosnek na plastry. 3. Wnętrze kaczki wypełnij jabłkami i czosnkiem. 4. Posmaruj kaczkę miodem i piecz w piekarniku przez 2 godziny w temperaturze 180°C.')
;


CREATE TABLE IF NOT EXISTS ingredients (
                                           id INTEGER PRIMARY KEY AUTOINCREMENT,
                                           name TEXT NOT NULL
);

-- Wstawienie składników do tabeli
INSERT INTO ingredients (name) VALUES
                                   ('Bazylia'),
                                   ('Brokuły'),
                                   ('Bułka tarta'),
                                   ('Burak'),
                                   ('Bulion warzywny'),
                                   ('Cebula'),
                                   ('Chili'),
                                   ('Czosnek'),
                                   ('Drożdże'),
                                   ('Fasola'),
                                   ('Feta'),
                                   ('Jabłko'),
                                   ('Jajka'),
                                   ('Kaczka'),
                                   ('Kiełbasa'),
                                   ('Kurczak'),
                                   ('Łosoś'),
                                   ('Makaron'),
                                   ('Majonez'),
                                   ('Masło'),
                                   ('Mąka'),
                                   ('Mięso mielone'),
                                   ('Mleko'),
                                   ('Mozzarella'),
                                   ('Musztarda'),
                                   ('Ocet'),
                                   ('Ogórek'),
                                   ('Oliwa z oliwek'),
                                   ('Oliwki'),
                                   ('Papryka'),
                                   ('Parmezan'),
                                   ('Passata'),
                                   ('Pieczywo'),
                                   ('Pomidor'),
                                   ('Pstrąg'),
                                   ('Ryż'),
                                   ('Ser'),
                                   ('Śledź'),
                                   ('Śmietana'),
                                   ('Tuńczyk'),
                                   ('Wołowina'),
                                   ('Ziemniak');


CREATE TABLE IF NOT EXISTS recipe_ingredients (
                                                  recipe_id INTEGER,
                                                  ingredient_id INTEGER,
                                                  FOREIGN KEY (recipe_id) REFERENCES recipes(id),
                                                  FOREIGN KEY (ingredient_id) REFERENCES ingredients(id),
                                                  PRIMARY KEY (recipe_id, ingredient_id)
);

INSERT INTO recipe_ingredients (recipe_id, ingredient_id) VALUES
(1, (SELECT id FROM ingredients WHERE name = 'Makaron')),
(1, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(1, (SELECT id FROM ingredients WHERE name = 'Oliwa z oliwek')),
(1, (SELECT id FROM ingredients WHERE name = 'Bazylia')),
(1, (SELECT id FROM ingredients WHERE name = 'Parmezan')),

(2, (SELECT id FROM ingredients WHERE name = 'Pomidor')),
(2, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(2, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(2, (SELECT id FROM ingredients WHERE name = 'Bulion warzywny')),
(2, (SELECT id FROM ingredients WHERE name = 'Oliwa z oliwek')),
(2, (SELECT id FROM ingredients WHERE name = 'Bazylia')),

(3, (SELECT id FROM ingredients WHERE name = 'Kurczak')),
(3, (SELECT id FROM ingredients WHERE name = 'Ziemniak')),
(3, (SELECT id FROM ingredients WHERE name = 'Oliwa z oliwek')),
(3, (SELECT id FROM ingredients WHERE name = 'Papryka')),
(3, (SELECT id FROM ingredients WHERE name = 'Czosnek')),

(4, (SELECT id FROM ingredients WHERE name = 'Pomidor')),
(4, (SELECT id FROM ingredients WHERE name = 'Ogórek')),
(4, (SELECT id FROM ingredients WHERE name = 'Papryka')),
(4, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(4, (SELECT id FROM ingredients WHERE name = 'Feta')),
(4, (SELECT id FROM ingredients WHERE name = 'Oliwki')),

(5, (SELECT id FROM ingredients WHERE name = 'Mięso mielone')),
(5, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(5, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(5, (SELECT id FROM ingredients WHERE name = 'Pomidor')),
(5, (SELECT id FROM ingredients WHERE name = 'Passata')),
(5, (SELECT id FROM ingredients WHERE name = 'Makaron')),

(6, (SELECT id FROM ingredients WHERE name = 'Brokuły')),
(6, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(6, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(6, (SELECT id FROM ingredients WHERE name = 'Bulion warzywny')),
(6, (SELECT id FROM ingredients WHERE name = 'Śmietana')),
(6, (SELECT id FROM ingredients WHERE name = 'Oliwa z oliwek')),

(7, (SELECT id FROM ingredients WHERE name = 'Ogórek')),
(7, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(7, (SELECT id FROM ingredients WHERE name = 'Ziemniak')),
(7, (SELECT id FROM ingredients WHERE name = 'Bulion warzywny')),
(7, (SELECT id FROM ingredients WHERE name = 'Śmietana')),

(8, (SELECT id FROM ingredients WHERE name = 'Ziemniak')),
(8, (SELECT id FROM ingredients WHERE name = 'Jajka')),
(8, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(8, (SELECT id FROM ingredients WHERE name = 'Ogórek')),
(8, (SELECT id FROM ingredients WHERE name = 'Majonez')),
(8, (SELECT id FROM ingredients WHERE name = 'Musztarda')),

(9, (SELECT id FROM ingredients WHERE name = 'Mięso mielone')),
(9, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(9, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(9, (SELECT id FROM ingredients WHERE name = 'Papryka')),
(9, (SELECT id FROM ingredients WHERE name = 'Passata')),
(9, (SELECT id FROM ingredients WHERE name = 'Fasola')),
(9, (SELECT id FROM ingredients WHERE name = 'Chili')),

(10, (SELECT id FROM ingredients WHERE name = 'Makaron')),
(10, (SELECT id FROM ingredients WHERE name = 'Tuńczyk')),
(10, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(10, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(10, (SELECT id FROM ingredients WHERE name = 'Oliwa z oliwek')),

(11, (SELECT id FROM ingredients WHERE name = 'Pstrąg')),
(11, (SELECT id FROM ingredients WHERE name = 'Masło')),
(11, (SELECT id FROM ingredients WHERE name = 'Bazylia')),

(12, (SELECT id FROM ingredients WHERE name = 'Mięso mielone')),
(12, (SELECT id FROM ingredients WHERE name = 'Jajka')),
(12, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(12, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(12, (SELECT id FROM ingredients WHERE name = 'Bułka tarta')),

(13, (SELECT id FROM ingredients WHERE name = 'Mąka')),
(13, (SELECT id FROM ingredients WHERE name = 'Jajka')),
(13, (SELECT id FROM ingredients WHERE name = 'Mleko')),

(14, (SELECT id FROM ingredients WHERE name = 'Fasola')),
(14, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(14, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(14, (SELECT id FROM ingredients WHERE name = 'Kiełbasa')),
(14, (SELECT id FROM ingredients WHERE name = 'Passata')),
(14, (SELECT id FROM ingredients WHERE name = 'Papryka')),

(15, (SELECT id FROM ingredients WHERE name = 'Wołowina')),
(15, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(15, (SELECT id FROM ingredients WHERE name = 'Jajka')),
(15, (SELECT id FROM ingredients WHERE name = 'Ogórek')),

(16, (SELECT id FROM ingredients WHERE name = 'Ziemniak')),
(16, (SELECT id FROM ingredients WHERE name = 'Makaron')),
(16, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(16, (SELECT id FROM ingredients WHERE name = 'Śmietana')),

(17, (SELECT id FROM ingredients WHERE name = 'Kurczak')),
(17, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(17, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(17, (SELECT id FROM ingredients WHERE name = 'Mleko')),
(17, (SELECT id FROM ingredients WHERE name = 'Papryka')),

(18, (SELECT id FROM ingredients WHERE name = 'Passata')),
(18, (SELECT id FROM ingredients WHERE name = 'Mozzarella')),
(18, (SELECT id FROM ingredients WHERE name = 'Bazylia')),
(18, (SELECT id FROM ingredients WHERE name = 'Oliwa z oliwek')),

(19, (SELECT id FROM ingredients WHERE name = 'Jajka')),
(19, (SELECT id FROM ingredients WHERE name = 'Majonez')),
(20, (SELECT id FROM ingredients WHERE name = 'Musztarda')),

(20, (SELECT id FROM ingredients WHERE name = 'Ziemniak')),
(20, (SELECT id FROM ingredients WHERE name = 'Jajka')),
(20, (SELECT id FROM ingredients WHERE name = 'Mąka')),

(21, (SELECT id FROM ingredients WHERE name = 'Ryż')),
(21, (SELECT id FROM ingredients WHERE name = 'Łosoś')),
(21, (SELECT id FROM ingredients WHERE name = 'Ogórek')),

(22, (SELECT id FROM ingredients WHERE name = 'Mąka')),
(22, (SELECT id FROM ingredients WHERE name = 'Drożdże')),
(22, (SELECT id FROM ingredients WHERE name = 'Oliwa z oliwek')),

(23, (SELECT id FROM ingredients WHERE name = 'Pomidor')),
(23, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(23, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(23, (SELECT id FROM ingredients WHERE name = 'Bulion warzywny')),
(23, (SELECT id FROM ingredients WHERE name = 'Śmietana')),

(24, (SELECT id FROM ingredients WHERE name = 'Kurczak')),
(24, (SELECT id FROM ingredients WHERE name = 'Ryż')),
(24, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(24, (SELECT id FROM ingredients WHERE name = 'Bulion warzywny')),
(24, (SELECT id FROM ingredients WHERE name = 'Parmezan')),

(25, (SELECT id FROM ingredients WHERE name = 'Pieczywo')),
(25, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(25, (SELECT id FROM ingredients WHERE name = 'Masło')),

(26, (SELECT id FROM ingredients WHERE name = 'Mąka')),
(26, (SELECT id FROM ingredients WHERE name = 'Jajka')),
(26, (SELECT id FROM ingredients WHERE name = 'Mleko')),

(27, (SELECT id FROM ingredients WHERE name = 'Burak')),
(27, (SELECT id FROM ingredients WHERE name = 'Ogórek')),
(27, (SELECT id FROM ingredients WHERE name = 'Jajka')),

(28, (SELECT id FROM ingredients WHERE name = 'Mąka')),
(28, (SELECT id FROM ingredients WHERE name = 'Masło')),
(28, (SELECT id FROM ingredients WHERE name = 'Jajka')),

(29, (SELECT id FROM ingredients WHERE name = 'Śledź')),
(29, (SELECT id FROM ingredients WHERE name = 'Cebula')),
(29, (SELECT id FROM ingredients WHERE name = 'Ocet')),

(30, (SELECT id FROM ingredients WHERE name = 'Kaczka')),
(30, (SELECT id FROM ingredients WHERE name = 'Czosnek')),
(30, (SELECT id FROM ingredients WHERE name = 'Jabłko'));





