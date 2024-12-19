# The Zoo
## Supervised model
### Classification problem

This model predict what animal is given in description.

Here is the characteristics of this model :
    
    Selected Attributes: 12,3,7,2,0,1,8,9,13,4,11,5,15,10,6,14,16
    [Most interesting features]


    J48 unpruned tree
    ------------------
    
    feathers = false
    |   milk = false
    |   |   backbone = false
    |   |   |   airborne = false
    |   |   |   |   predator = false
    |   |   |   |   |   legs <= 2: invertebrate (2.0)
    |   |   |   |   |   legs > 2: insect (2.0)
    |   |   |   |   predator = true: invertebrate (8.0)
    |   |   |   airborne = true: insect (6.0)
    |   |   backbone = true
    |   |   |   fins = false
    |   |   |   |   tail = false: amphibian (3.0)
    |   |   |   |   tail = true: reptile (6.0/1.0)
    |   |   |   fins = true: fish (13.0)
    |   milk = true: mammal (41.0)
    feathers = true: bird (20.0)
    
    Number of Leaves  : 	9
    
    Size of the tree : 	17

Creating a new animal, let's say an `unicorn` that has this description : `with hair, no feather, no eggs, has milk, not airborn, not quatic, not predator, with teeth, with backbone, with breathes, venomous, no fins, with 4 legs, with tail, domestic, no catsize`,
the model predict that this animal is a `mammal`.
    
    The unicorn is a mammal
