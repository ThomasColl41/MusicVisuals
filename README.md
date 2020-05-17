# Music Visualiser Project

Name: Thomas Coll

Student Number: C18332616

# Description of the assignment

This assignment features a number of shapes drawn in a circle. While different shapes can be seen, they are all Shape objects. This is an example of polymorphism. The number of shapes can be adjusted, since the sketch responds to keyboard input. Two sets of lines, also arranged in circles, envelop the shapes both internally and externally. All visuals respond in some way to the music being played.

# Instructions

The following list consists of all the keyboard buttons that can be pressed to alter the sketch. Each command ultimately changes everything that is displayed. These instructions are also displayed while the sketch is running:
- 'q' to add a new Shape object to the circle
- 'e' to delete a Shape
- 'spacebar' to randomly change all the Shapes 
- 'a' to toggle auto mode on/off

# How it works

## Circle of Shapes

As mentioned above, polymorphism is used to manipulate what gets displayed. An ArrayList (shapes) holds the Shape objects. Each shape is a separate class, and thus, each have their own file, fields and render method. The Shape class consists of an x1 and y1, which is common to all subclasses. Its render method will, in theory, never be called.

To draw the circle of shapes, the origin (0,0) is translated into the centre of the sketch. This serves as the centre of the circle. Another translation is performed, and this time will represent the centre of each shape. The second translation co-ordinates are found using trigonometry, mapped to the size of the ArrayList to ensure equal spacing. Each Shape's render method is then called. The specific render method called is determined by the type of Shape subclass the object is (polymorphism).

## Other elements

The sets of lines are drawn in a similar way to the circle of shapes. They share a common origin point in the middle of the sketch (but translate is called for each). Both sets of lines consist of the same number of lines and reflect the total number of shapes on-screen. The inside lines rotate at a constant speed. In terms of reaction to music (audioBuffer), the inside lines expand outwards (if they weren't rotating) and the outside lines grow inwards, towards the centre.

## Visual effects

A fader method was created to fade the stroke colour of shapes from black to white and back from white to black. The fader uses an enumerator to control whether the stroke colour is increasing (black to white) or decreasing (white to black). Otherwise, the outline of each shape would transition from black to white and then straight back to black.

The stroke colour for the inside group of lines is grayscale and based on the amplitude of the audioBuffer. The outside lines remain at constant stroke colour. In relation to HSB colours, the hue for the shapes is mapped based on the number of current shapes. There is also a slight offset, which results in the HSB colourspace being cycled through in a clockwise-direction.

## Auto mode

In order to implement an automatic feature, a new class, Control was created. It consists of an enumerator that represents whether auto mode is on or off. Once enabled, the FFT bands of the audioBuffer are examined. Once pre-determined bands reach a pre-determined frequency (obtained through testing), certain methods are called e.g. shapes will be added/deleted. Booleans control how often auto methods are called. Once a certain method is called, that particular method must wait a defined number of frames before it can be called again. The user can still adjust the sketch using keyboard input while auto is enabled.

# What I am most proud of in the assignment

I am particualrly proud of how the circle of shapes turned out in the end. I had a few ideas on how it could work, which were refined over time. The class structure and use of polymorphism made it a lot easier to change shapes than was originally imagined. At first, changing the Shapes involved deleting them all and creating the same number again. This was not real polymorphism in my opinion. After revising over polymorphism, it soon became clear that the elements of the ArrayList could just be re-assigned with a diiferent constructor. This was made possible using the  ArrayList's set() method.

# Link to YouTube video

Here is the link to the assignment video presentation:

[![YouTube](https://i9.ytimg.com/vi/w8MjuZ3lvhw/mqdefault.jpg?time=1589749241639&sqp=CNDLhvYF&rs=AOn4CLA-8Kir_mpR3m_q1SFVih2E7HgR6g)](https://youtu.be/w8MjuZ3lvhw)
