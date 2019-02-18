/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javacv.simple.face.detection;

import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.RectVector;
import org.bytedeco.javacpp.opencv_objdetect.CascadeClassifier;

import org.bytedeco.javacpp.opencv_core.Point;
import org.bytedeco.javacpp.opencv_core.Rect;
import org.bytedeco.javacpp.opencv_core.Scalar;

import static org.bytedeco.javacpp.opencv_highgui.imshow;
import static org.bytedeco.javacpp.opencv_highgui.moveWindow;
import static org.bytedeco.javacpp.opencv_highgui.waitKey;
import static org.bytedeco.javacpp.opencv_imgproc.rectangle;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

/**
 * A simple JavaCV face detection application.
 *
 * @author Momir Sarac
 * @version 1.0
 */
public class JavaCVSimpleFaceDetection {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // utility params for paths of required files
        String imagePath = System.getProperty("user.dir") + "/files/face.jpg";
        String haarCascadePath = System.getProperty("user.dir") + "/files/haarcascade_frontalface_alt2.xml";

        // create a Mat object out of a read image file
        Mat image = imread(imagePath);
        // a resultant classifier of several plain stages applied 
        // subsequently to an image until at some stage the candidate is rejected or
        // all the stages are passed.
        CascadeClassifier faceDetector = new CascadeClassifier(haarCascadePath);
        // a list of rectangles used for face detections
        RectVector faceDetections = new RectVector();
        // Detects objects of different sizes in the input image. 
        // The detected objects are returned as a list of rectangles, see faceDetections above.
        faceDetector.detectMultiScale(image, faceDetections);
        // for-each loop
        for (Rect rect : faceDetections.get()) {
            //create a new rectangle with provided params on a Mat object
            rectangle(image, new Point(rect.x(), rect.y()), new Point(rect.x() + rect.width(), rect.y() + rect.height()), Scalar.GREEN);
        }
        // show window with a title Face_detection and a provided Mat object
        imshow("Face_detection", image);
        // move window at a given location on screen x:600,y:200
        moveWindow("Face_detection", 600, 200);
        // show window until any key pressed
        waitKey();

    }

}