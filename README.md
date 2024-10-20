<h1>Real-Time Surveillance System with Facial Recognition Based on Deep Learning.</h1>
<h3>Overview</h3>
This project aims to develop an advanced real-time surveillance system using facial recognition.
Through Deep Learning technologies, we aim to collect, analyze, and provide real-time information on identified individuals via a facial recognition API.
    <h3>Application Overview</h3>
    <ul>
        <li>
            <strong>User Interface:</strong>
            <ul>
                <li>Developed using <strong>FastAPI</strong>, allowing for quick and efficient web API development.</li>
                <li>Users can <strong>upload an image</strong> through a designated endpoint (<code>/uploadImage/</code>) for facial recognition processing.</li>
            </ul>
        </li>
        <li>
            <strong>Image Processing:</strong>
            <ul>
                <li>Upon image upload, the application uses the <strong>MTCNN</strong> (Multi-task Cascaded Convolutional Networks) to detect faces in the image.</li>
                <li>Extracted faces are resized to <code>(224, 224)</code> pixels for uniformity before further processing.</li>
            </ul>
        </li>
        <li>
            <strong>Facial Recognition:</strong>
            <ul>
                <li>The application employs the <strong>VGGFace</strong> model for facial recognition, extracting features from the detected faces.</li>
                <li>Encoded face representations are created using the <code>encode_images</code> function, generating embeddings that capture essential facial features.</li>
            </ul>
        </li>
        <li>
            <strong>Hashing for Matching:</strong>
            <ul>
                <li>Generated face encodings are converted into <strong>binary hash codes</strong> using a thresholding technique, allowing for efficient comparison between known faces and uploaded images.</li>
                <li>A <strong>Hamming distance</strong> metric is used to compare the hash codes and determine the similarity between faces.</li>
            </ul>
        </li>
        <li>
            <strong>Database Integration:</strong>
            <ul>
                <li>The application maintains an Excel file where each entry corresponds to an individual. This file includes essential information such as the Image URL, which points to the individual’s photo, along with their personnal informations. This structured data allows the application to provide detailed match information during the facial recognition process.</li>
                <li>Upon startup, the application reads the Excel file using Pandas, extracting images and relevant details into a dictionary for quick access. This ensures efficient lookups during face matching. The faces are then pre-processed and encoded to create a reference dataset, which is used for comparison against the images uploaded by users..</li>
            </ul>
        </li>
        <li>
            <strong>Real-Time Processing:</strong>
            <ul>
                <li>The application integrates with <strong>Apache Kafka</strong> for real-time data processing:
                    <ul>
                        <li>Uploaded images are sent to a Kafka topic (<code>facial_recognition_results</code>).</li>
                        <li>A separate consumer thread listens for images on this topic, processes them, and matches them against the known faces.</li>
                        <li>Results, including match information, are produced to another Kafka topic (<code>facial_recognition_results_display</code>).</li>
                    </ul>
                </li>
            </ul>
        </li>
    </ul>
    <h3>Project architeture</h3>
    <img width="600" alt="Capture d’écran 2024-10-20 152540" src="https://github.com/user-attachments/assets/5ff7af3c-d9ae-42f7-9aff-f1af0660ac7c">
    <h3>Techniques Used</h3>
    <ul>
        <li>
            <strong>FastAPI:</strong>
            <ul>
                <li>Framework utilized for building the web API, providing high performance and automatic generation of API documentation.</li>
            </ul>
        </li>
        <li>
            <strong>MTCNN:</strong>
            <ul>
                <li>Deep learning model used for face detection, identifying faces within images accurately.</li>
            </ul>
        </li>
        <li>
            <strong>VGGFace:</strong>
            <ul>
                <li>A deep learning model employed for extracting facial embeddings, enabling the system to recognize and differentiate between various individuals.</li>
            </ul>
        </li>
        <li>
            <strong>Numpy</strong> and <strong>Pandas:</strong>
            <ul>
                <li>Libraries used for numerical computations and data manipulation, facilitating image processing and database handling.</li>
            </ul>
        </li>
        <li>
            <strong>Apache Kafka:</strong>
            <ul>
                <li>Messaging system used for asynchronous processing, enabling the application to handle image uploads and matches in real-time.</li>
            </ul>
        </li>
        <li>
            <strong>Keras:</strong>
            <ul>
                <li>Library used for building and training deep learning models, providing the VGGFace architecture for facial recognition tasks.</li>
            </ul>
        </li>
        <li>
            <strong>Threading:</strong>
            <ul>
                <li>Python threading is used to run the Kafka consumer in a separate thread, allowing the FastAPI application to handle incoming HTTP requests simultaneously.</li>
            </ul>
        </li>
        <li>
            <strong>Image Handling:</strong>
            <ul>
                <li>Utilizes <strong>OpenCV</strong> for image decoding and processing, ensuring images are correctly formatted for analysis.</li>
            </ul>
        </li>
    </ul>

</body>
</html>
