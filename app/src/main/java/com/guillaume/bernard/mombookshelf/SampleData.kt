package com.guillaume.bernard.mombookshelf

import com.guillaume.bernard.mombookshelf.model.Book

object SampleData {

    val books: Array<Book> = arrayOf(
        Book(
            id = 1L,
            title = "1894",
            published = 1949,
            author ="George Orwell",
            isbn = "978-2070368228",
            genre = "Sci-fi",
            description = "De tous les carrefours importants, le visage à la moustache noire vous fixait du regard. BIG BROTHER VOUS REGARDE, répétait la légende, tandis que le regard des yeux noirs pénétrait les yeux de Winston... Au loin, un hélicoptère glissa entre les toits, plana un moment, telle une mouche bleue, puis repartit comme une flèche, dans un vol courbe. C'était une patrouille qui venait mettre le nez aux fenêtres des gens. Mais les patrouilles n'avaient pas d'importance. Seule comptait la Police de la Pensée"
        ),
        Book(
            id = 2L,
            title = "L'Étranger",
            published = 1942,
            author = "Albert Camus",
            isbn = "978-2070360024",
            genre = "Novel",
            description = "Quand la sonnerie a encore retenti, que la porte du box s'est ouverte, c'est le silence de la salle qui est monté vers moi, le silence, et cette singulière sensation que j'ai eue lorsque j'ai constaté que le jeune journaliste avait détourné les yeux. Je n'ai pas regardé du côté de Marie. Je n'en ai pas eu le temps parce que le président m'a dit dans une forme bizarre que j'aurais la tête tranchée sur une place publique au nom du peuple français..."
        ),
        Book(
            id = 3L,
            title = "Harry Potter and the philosopher stone",
            published = 1997,
            author = "J.K. Rowling",
            isbn = "978-2070643028",
            genre = "Fantasy",
            description = "Le jour de ses onze ans, Harry Potter, un orphelin élevé par un oncle et une tante qui le détestent, voit son existence bouleversée. Un géant vient le chercher pour l’emmener à Poudlard, une école de sorcellerie! Voler en balai, jeter des sorts, combattre les trolls : Harry Potter se révèle un sorcier doué. Mais un mystère entoure sa naissance et l’effroyable V…, le mage dont personne n’ose prononcer le nom. Amitié, surprises, dangers, scènes comiques, Harry découvre ses pouvoirs et la vie à Poudlard. Le premier tome des aventures du jeune héros vous ensorcelle aussitôt!"
        ),
        Book(
            id = 4L,
            title = "Harry Potter and the chamber of secret",
            published = 1997,
            author = "J.K. Rowling",
            isbn = "978-2070643028",
            genre = "Fantasy",
            description = "Une rentrée fracassante en voiture volante, une étrange malédiction qui s’abat sur les élèves, cette deuxième année à l’école des sorciers ne s’annonce pas de tout repos! Entre les cours de potions magiques, les matches de Quidditch et les combats de mauvais sorts, Harry et ses amis Ron et Hermione trouveront-ils le temps de percer le mystère de la Chambre des Secrets? Le deuxiè me volume des aventures de Harry Potter : un livre magique pour sorciers confirmés."
        ),
        Book(
            id = 5L,
            title = "Harry Potter and the prisoner of Azkaban",
            published = 1999,
            author = "J.K. Rowling",
            isbn = "978-2070584925",
            genre = "Fantasy",
            description = "Sirius Black, le dangereux criminel qui s'est échappé de la forteresse d'Azkaban, recherche Harry Potter. C'est donc sous bonne garde que l'apprenti sorcier fait sa troisième rentrée. Au programme : des cours de divination, la fabrication d'une potion de Ratatinage, le dressage des hippogriffes... Mais Harry est-il vraiment à l'abri du danger qui le menace ?"
        ),
        Book(
            id = 6L,
            title = "Harry Potter and the goblet of fire",
            published = 2000,
            author = "J.K. Rowling",
            isbn = "978-2070585205",
            genre = "Fantasy",
            description = "Harry Potter a quatorze ans et entre en quatrième année à Poudlard. Une grande nouvelle attend Harry, Ron et Hermione à leur arrivée : la tenue d'un tournoi de magie exceptionnel entre les plus célèbres écoles de sorcellerie. Déjà les délégations étrangères font leur entrée. Harry se réjouit... Trop vite. Il va se trouver plongé au coeur des événements les plus dramatiques qu'il ait jamais eu à affronter."
        ),
        Book(
            id = 7L,
            title = "Harry Potter and the phoenix order",
            published = 2003,
            author = "J.K. Rowling",
            isbn = "978-2070556854",
            genre = "Fantasy",
            description = "À quinze ans, Harry s'apprête à entrer en cinquième année à Poudlard. Et s'il est heureux de retrouver le monde des sorciers, il n'a jamais été aussi anxieux. L'adolescence, la perspective des examens importants en fin d'année et ces étranges cauchemars... Car Celui-Dont-On-Ne-Doit-Pas-Prononcer-Le-Nom est de retour et, plus que jamais, Harry sent peser sur lui une terrible menace. Une menace que le ministère de la Magie ne semble pas prendre au sérieux, contrairement à Dumbledore. Poudlard devient alors le terrain d'une véritable lutte de pouvoir. La résistance s'organise autour de Harry qui va devoir compter sur le courage et la fidélité de ses amis de toujours..."
        ),
    )

    val genres = arrayOf("Thriller", "Fantasy", "Sci-Fi", "Comic", "Cooking", "Biography")

}