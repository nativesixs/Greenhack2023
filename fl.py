from PIL import Image
import pytesseract
import numpy as np

# pytesseract.pytesseract.tesseract_cmd = r'C:\Program Files\Tesseract-OCR\tesseract.exe'

# filename = 'img.png'
# img1 = np.array(Image.open(filename))
# text = pytesseract.image_to_string(img1)




# h=7+7+6+8+6+6+8+7+6+7+7+6
# m=(55+25+40+25+55+50+5+5+5+10+15)/60

# print(h+m+6.5+6.9)

# print(text)




from flask import Flask, render_template, request
 
app = Flask(__name__, template_folder='flht', static_folder='staticFiles')
 

 
@app.route('/home', methods=['GET', 'POST'])
def home():
    return render_template('home.html')


@app.route('/dpubind', methods=['GET', 'POST'])
def dpubind():
        return render_template('dpubind.html')

@app.route('/ipsetswitch', methods=['GET', 'POST'])
def ipsetswitch():
    return render_template('ipsetswitch.html')


@app.route('/terminateEdge', methods=['GET', 'POST'])
def terminateEdge():
    if request.method == "POST":
        ckod=request.form['ckod']
        hello(ckod)
        print(ckod)

    return render_template('terminateEdge.html')

def hello(ckod):
    print("hello "+ckod)
 
if __name__=='__main__':
    app.run(debug = False, port=9090)