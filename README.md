

-   main loop:
    - 60 fps ma gra
    - snake idzie w ostatni podany kierunek 

-   grid layout 640x640 (32x32) wypelniony pane'ami:
    - prawie bialy -> rgba(183, 183, 183, 0.51)
    - prawie czarny -> rgba(0, 0, 0, 0.30)

  - snake:
    - glowa jego to kolko w kolorze -> #c76b6b
    - cale cialo ma taki sam kolor jak glowa
    - po zjedzeniu jablka dodaje mu sie dlugosc o 32x32
    - jesli dotknie scianek aplikacji ginie i wyswietla sie alert ze przegral

Instead of implementing the snake as an array, why not implement it as a linked list, where each entry in the list is a point where the snake's body turns? For example, you could have the list {<5,5>, <8,5>, <8,10>, <12,10>} with <5,5> being the head, <12,10> being the tail, and the points in the middle being curves in the snake's body. Then when the player turns the snake, you only have to add a new entry to the front of the list, and when the snake moves you just update the tail until it overlaps the next point, at which time you remove it.