# ImageProcesing

## Design Decisions:

An image, represented by the MyImage interface, in this program is represented by a collection of pixels, represented by the Pixel
interface. A Pixel object is able to accept an operation that is performed on this pixel, represented by the PixelOperation interface;
this was done so addition operations can be done to Pixel objects without having to change the Pixel interface.

The SimplePixel class implements the Pixel interface and represents a pixel with 3 components (the RGB components) and where it's 
represented in 8-bit. This was done as currently we only need to support PPM files which only need those 3 components in 8-bit.

All the classes that currently implement the PixelOperation interface (RedGreyscalePixelOperation, BlueGreyscalePixelOperation,
GreenGreyscalePixelOperation, ValueGreyscalePixelOperation, IntensityGreyscalePixelOperation, LumaGreyscalePixelOperation, 
BrightenGreyscaleOperation, and DarkenGreyscaleOperation) represent the operation they are named after and executes that operation
onto a pixel (for example, the execute method in the RedGreyscaePixelOperation greyscales the given pixel using the red component of
the given pixel). 

Similar to Pixel objects, MyImage objects are also able to accept an operation that is perform onto it, represented by the ImageOperation
interface. This is done for the same reason as why Pixels are able to accept operations. Additionally, MyImage objects can also return
a copy of it's self so the model can easily create copies of the images and perform operations on them while keeping the original image 
unchanged. The flipping operations (horizontal and vertical flip) are written as methods within the MyImage interface as direct access to
the collection of pixels is needed to change it and we didn't want to return a reference to the collection. 

The SimpleImage class implements the MyImage interface and represents an implementation of this interface. Similar to the classes that 
implement the PixelOperation interface, the classes that implement the ImageOperation interface represent the operation they are named
after and executes the operation onto an image. The operations that need to iterate through the collection of pixels and execute the same
PixelOperation on each pixel was abstracted so they would have access to a helper that reduces code duplication. The ImageOperations that
use this abstract class are RedGreyscaleImageOperation, GreenGreyscaleImageOperation, BlueGreyscaleImageOperation, ValueGreyscaleImageOperation,
IntensityGreyscaleImageOperation, LumaGreyscaleOperation, BrightenImageOperation, and DarkenImageOperation. 

The ImageProcessingModel interface represents a model for an image processing application. It is able to store multiple MyImage objects,
load files into this model as MyImage objects, perform operations on the stored MyImage objects, and write files from the data within the
stored MyImage objects. Additionally, it is able to return a MyImage object stored within it so the view is able to observe these images.

The ImageProcessingModelImpl class represents a representation of the ImageProcessingModel interface. It uses a HashMap to store the 
collection of MyImage objects as it also needs to store a name that it's refered to within the program and the names are unique. For now,
the load and save methods throw an exception if the file name they are given isn't a ppm file as this model is only able to read and write
ppm files for this version.

The ImageProcessingView interface represents a view for an image processing application. Currently it's only use is to render messages as
displaying images isn't available for this version. The ImageProcessingTextView implements the ImageProcessingView and uses an appendable to
renderMessages.

The ImageProcessingController interface represents the controller for an image processing application. It's only method is to execute the program.
The ImageProcessingControllerImpl is an implementation of the ImageProcessingController interface. It's fields include an ImageProccessingModel,
an ImageProcessingView, and a Scanner. This implementation follows the command design pattern and uses a HashMap to store the known commands
to reduce having long code chunks (having a super long switch statement with a case for every command and having a lot of code within each of 
those cases). This implementation also has a private method that renders the menu of options and gives feedback on commands performed (if they were
successful or not and what caused them to be unsuccessful if they were) so the user is able to see what they're doing without being able to see 
the actual image.

The ImageProcessingCommand interface represents the commands that can be run in the ImageProcessingController and has a method to execute the command
onto the model.The ImageCommand class implements represents any command that edits a picture in this application as the model has the capability to 
modify one of the images that it stores with a provided ImageOperation. The Load and Save commands use the methods in the model to load and save 
images to the model.

## How to run this program:

To run the program, no command line arguments are required.
When the program runs, first load an image into the program by entering this command into the console:

load *file-path* *image-name*

where file-path is the path of the image on your system and image-name is the name you want to refer to it as within the program.
For example, if you wanted to load the Landscape.ppm image provided in the res/ folder and call it "landscape", you would enter:

load res/Landscape.ppm landscape

While the programming is running, you can load additional images into the program. You are able to override images stored in 
this program by loading an image using a different file path and the same image name.

To run operations on the images loaded into the program, you are able to choose from these commands:

red-greyscale *image-name* *new-image-name*

green-greyscale *image-name* *new-image-name*

blue-greyscale *image-name* *new-image-name*

value-greyscale *image-name* *new-image-name*

intensity-greyscale *image-name* *new-image-name*

luma-greyscale *image-name* *new-image-name*

flip-horizontally *image-name* *new-image-name*

flip-vertically *image-name* *new-image-name*

brighten *image-name* *new-image-name* *value*

darken *image-name* *new-image-name* *value*

where image-name refers to the name of an image already loaded into this program, new-image-name is the name the changed image will
be stored under in this program, and value (only applicable for brighten and darken right now) is the amount this operation should
change this image by. For example, if you wanted to brighten by a value of 20 and then flip vertically the landscape image we already
loaded and call it landscape-bright-then-flip, you would enter these commands:

brighten landscape landscape-bright 20

flip-vertically landscape-bright landscape-bright-then-flip

The original image doesn't change when you perform operations on it. To save an image onto your system, enter this command:

save *file-path* *image-name*

where file-path is where the image should be saved on your machine and image-name is the name of an image stored in this program.
For example, if you wanted to save the image we just performed some operations on to res/Landscape-bright-then-flip.ppm, you would
enter this command:

save res/Landscape-bright-then-flip.ppm landscape-bright-then-flip

To quit this program, enter 'q' or "quit" into the console.

## Image citation:

Landscape.ppm, included within the res/ folder, is photographed and owned by one of the contributors on this project (Sharon Yang,
yang.shar@northeastern.edu) and is authorized to be used in this project.
