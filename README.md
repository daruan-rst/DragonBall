# DragonBall inspired API
```
   __
  |  ""--.--.._                                             __..    ,--.
  |       `.   "-.'""\_...-----..._   ,--. .--..-----.._.""|   |   /   /
  |_   _    \__   ).  \           _/_ |   \|  ||  ..    >  `.  |  /   /
    | | `.   ._)  /|\  \ .-"""":-"   "-.   `  ||  |.'  ,'`. |  |_/_  /
    | |_.'   |   / ""`  \  ===/  ..|..  \     ||      < ""  `.  "  |/__
    `.      .    \ ,--   \-..-\   /"\   /     ||  |>   )--   |    /    |
     |__..-'__||__\   |___\ __.:-.._..-'_|\___||____..-/  |__|--""____/
     
```
This is a simple API inspired by the manga and anime written by Akira Toryiama (or at least how i remember it to be)

Here are the challenges I set myself to do while making this project:

<h2></h2>

1. The entities :

The dragon ball manga was written in a fantasy world where many planets existed, in which a lot of cities were built and a lot of people lived.
So there should be at least these three main entities

 - The franchise often presents us with a lot of [planets](https://dragonball.fandom.com/wiki/List_of_races), some of which are blessed with having a set of dragon balls ( for instance, namek and earth);
 - The planets have cities, as Earth has it's [North City](https://dragonball.fandom.com/wiki/North_City), [West City](https://dragonball.fandom.com/wiki/West_City) and [Central City](https://dragonball.fandom.com/wiki/Central_City) ;
 - The people that inhabit every planet may come in different [races](https://dragonball.fandom.com/wiki/List_of_races) (as in namek, sayans, earthlings etc). They all have their name and are more often than not frisked for their "power level". Also, as shown several times, everyone can die (and appear with a halo);

2. What should it do?:

  - The main CRUD tasks for every entity;
  - The soldiers of the Frieza army often wear scouters that can measure someone's power level. So there must be way to filter habitants 
  by their power level ( and also because of that ["it's over 9000"](https://www.youtube.com/watch?v=QsDDXSmGJZA) meme)
  - When a villan shows up a city (if not more than that) is always destroyed. A @DeleteMapping should be made so that not only the City is removed from the City table, but also created in the DestroyedCity one
  - The villains also kill a lot of people. There must be a method to kill any habitant;
  - When all the dragon balls are gathered in the same place, a dragon can be summoned (but remember The dragon will only be summoned if a Namek asks for it). 
   When the dragon appears, any wish can be grantted. Cities that were once destroyed can come back and people that once were killed be brought back to life. But remember: wishes can only happen if all the conditions are satisfied


 
<h2>Tecnologies:</h2><a id="tecnologies"></a>
  
 <ul>
  <li>Java 11</li>
  <li>Maven</li>
  <li>Spring Boot</li>
  <li>JPA</li>
  <li>Hibernate</li>
  <li>H2</li>
  <li>Lombok</li>
  </ul>
  

     
