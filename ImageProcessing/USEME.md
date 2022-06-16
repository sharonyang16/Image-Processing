# ImageProcessing

## Running the Program
To use this program, no command-line arguments are needed to run the program through giving inputs (typing)
to the console/terminal. Optionally, the program can also accept a script file by entering the following 
as a command-line argument:

-file script-file.txt

where "script-file" is the name of your script file. If an invalid file is provided, the program will
automatically resort to running as if there were no command-line arugments given.

Here's an example of how to use a script file:
1. Open a command-prompt/terminal and navigate to the provided res folder.
2. Type "java -jar ImageProcessing.jar -file example-script.txt" and hit enter.
This should create 4 images in the res folder all named starting with the word "example".

## Script Commands
To be able to use any of the script commands, an image needs to be loaded into the program first. To load in an image
enter the following:

load file-path image-name

where "file-path" is the path of the image on your system and "image-name" is the name you want to refer to it as
within the program. While the program is running, you can load additional images into the program anytime.

You are able to override images stored in this program by loading an image using a different file path and the same image name.

Here's an example of overriding an image name using load:
1. You load an image from ".\Landscape.png" and call it landscape using the following script command:
load .\Landscape.png landscape
2. You decide you actually want to have an image from ".\Landscape.ppm" be called landscape in the program so you enter:
load .\Landscape.ppm landscape
Now landscape refers to the image loaded in from ".\Landscape.ppm" instead of the one from ".\Landscape.png".

After loading an image, you are now able to run operations on those images. These operations do not modify the image original
image saved in this program, rather, it modifies a copy of the image and saves it to a new name. These script commands are
available to modify images loaded into the program:

red-greyscale image-name new-image-name

green-greyscale image-name new-image-name

blue-greyscale image-name new-image-name

value-greyscale image-name new-image-name

intensity-greyscale image-name new-image-name

luma-greyscale image-name new-image-name

flip-horizontally image-name new-image-name

flip-vertically image-name new-image-name

brighten image-name new-image-name value

darken image-name new-image-name value

blur image-name new-image-name

sharpen image-name new-image-name

greyscale image-name new-image-name

sepia image-name new-image-name

where "image-name" refers to the name of the image in this program and "new-image-name" is the new image that will be saved
in this program where the image associated with it has been transformed. For brighten and darken, "value" refers to the value
it's being brightened/darkened by. 
*Note: greyscale and luma-greyscale do the same thing*

Here's an example of using these image modifying script commands:
1. You want to brighten the image we loaded in the previous example ("landscape") by 32 and call it landscape-brighten so you enter:
brighten landscape landscape-bright 32
2. But you also want to blur that brightened image and call it landscape-bright-n-blur so you enter:
blur landscape-bright landscape-bright-n-blur
Now you have 3 images in your program: "landscape" which refers to the original image loaded in from ".\Landscape.ppm",
"landscape-brighten" which refers to "landscape" brightened by a factor of 32, and
"landscape-bright-n-blur" which refers to a blurred version of "landscape-brighten".
You are able to access all 3 of these during the time the program is running.

To save an image, enter this script command:

save file-path image-name

where "file-path" is where you want the image to be saved on your system and "image-name" is the name it is refered to within
the program. Note that only ppm, jpg, png, and bmp files are supported. However, you are able to mix and match in terms of
loading and saving. For example, you could load in a JPG and save it as a PNG or load in a BMP and save it as a PPM.

Here's an example of saving:
1. You want to save the "landscape-bright-n-blur" (from our previous example) at ".\Landscape-bright-n-blur.png" so you enter:
save .\Landscape-bright-n-blur.png landscape-bright-n-blur
2. You also want to save the original landscape image at ".\Landscape-jpg.jpg" so you enter:
save .\Landscape-jpg.jpg" landscape