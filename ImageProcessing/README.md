# ImageProcessing

## Completed Parts of the Program
All the features from Assignment 4 still work (i.e. load, save, red-greyscale, blue-greyscale, green-greyscale, value-greyscale,
intensity-greyscale,luma-greyscale, brighten, darken, flip-horizontally, flip-vertically).

All the features required in this assingment are also completed; the program can save and load ppm, jpg, png, and bmp, the program
can blur, sharpen, greyscale, and sepia the same way as the script commands from Assignment 4, the program can run with and without 
a script file, an example script file was written with all the operations, and a working JAR file of our program was made (see the
example under "Running the Program" in the USEME.md for how to run the script file with the JAR).

## Design Changes
With the introduction of the new filtering and color transformation operations, we realized the Pixel interface provide the flexibilty
needed to implement these operations i.e. the two void methods, greyscaleWith and adjustBrightness, that we had were not flexible enough to
be able to be used here. So we ended up writing setters for the RGB components. 

With having to implement loading and saving for JPG, PNG, and BMP, we felt that we needed to save the alpha component (which our design
had not previously done as PPM doesn't support the alpha channel). To avoid changing what the Pixel class represented, we extended the 
interface with a new interface that represented a pixel that supported the alpha component (TransparentPixel). We then could implement
a pixel that supported RGBA by extending the the original implmentation of the Pixel interface (the RGBPixel class, previously known as
SimplePixel but was changed to clarify what it represented) and implement the new TransparentPixel interface. This implementation is the
RGBAPixel class. Since RGBAPixel is both a Pixel and a TransparentPixel, it's able to use the operations that are specific to Pixel (the 
operations that extend the PixelOperation interface) and able to support the old operations. However, if there were new operations in the
future that required change on the alpha channel specifically, a new interface would have to be written (likely named TransparentPixelOperation) 
to be able to perform those operations. Additionally, the new interface was written to have a setter for the alpha component as we would 
likely encounter the same problem as not having setters for the RGB componenets of Pixel.

In order for images to be saved as PNGs, we realized that all images saved within the program would have to have the alpha channel
and we had to change the design of MyImage (interface) and SimpleImage (class) to represent a collection of TransparentPixel objects rather 
than Pixel objects. Images loaded in without an alpha component would just be opaque (their alpha component set to 255 for 8-bit). When 
reading and writing images, the images would not be able to read or write the alpha component without changing the fact that a MyImage object
should represnt a collection of TransparentPixel objects. Creating a new interface and class to represent images that support transparency would
mean the operations that extend ImageOperation would not work for this new interface and class. So now all of the images loaded into the model are saved as SimpleImage objects which holds a 2D ArrayList of TransparentPixel objects and the images that are loaded in from file formats that don't support the alpha channel have all their pixel's alpha components set to 255 (a constructor for RGBAPixel was written for this case).

An extra sentence was added after the "Command successful!" message in the controller after successfully completing commands to better the
user experience by confirming what command was just executed (it's especially helpful when running the program with a script file).