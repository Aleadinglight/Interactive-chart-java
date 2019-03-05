# Interactive Chart On Energy Consumption

School's Java project.

## About

An interactive chart for tracking consumption. You can interact with the chart by clicking on the column to see the data at that
time. And if you want to go back, simple press “Back” button at the left bottom to
review the previous scene.

## Usage

There is already a link inside StartApp.java, line 26. Please change this link to the
directory that you story this data.

```
StringBuilder DataLink= new StringBuilder("D:/ProjectJava");
```

The data is construct as a tree, at the top of the tree
is the view for all the years, when you travel down the tree, the will be
subdirectories indicate the following time. The data of a directory “2017/2/1”is
store in data.txt – meaning the power consumption at the first week of February
of 2017.

## More on details
First, there is 4 packages. In the default package is the StartApp.java. When you
run it. The app will be started. It will call the Draw.java in Chart Package to draw
the chart. The data is read by using the ReadFile.java in Reader Package. The
CreateData Package is used for creating an abstract data, use this if you somehow
lost the data.

There is already pre-made data in ProjectJava folder, you can use it as well. Please note that this data is just abstract data, it is not real because I want to keep it simple.

If there is anything that not clear you can look at the comments for more details.

## Example:
![Example:](https://github.com/Aleadinglight/data-tracking-consumption/blob/master/example.JPG)

