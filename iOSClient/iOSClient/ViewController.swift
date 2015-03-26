/*
The MIT License (MIT)

Copyright (c) <2015> <Dario A Lencina Talarico>

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
*/

import UIKit

class ViewController: UIViewController {
    
    let serverURL = "http://localhost:9000"

    override func viewDidLoad() {
        super.viewDidLoad()
        sayHiToTheServer()
        sendSomeRandomTextToTheServer()
        // Do any additional setup after loading the view, typically from a nib.
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func sayHiToTheServer() {
        let url = NSURL(string: serverURL+"/hiServer")
        
        let task = NSURLSession.sharedSession().dataTaskWithURL(url!) {(data, response, error) in
            println(NSString(data: data, encoding: NSUTF8StringEncoding))
        }
        
        task.resume()
    
    }
    
    func sendSomeRandomTextToTheServer(){
        let textToSend = NSString(format: "textToEcho=%@", NSDate().description)
        let httpBody = textToSend.dataUsingEncoding(NSUTF8StringEncoding)
        
        var request = NSMutableURLRequest(URL: NSURL(string: serverURL+"/echo")!)
        request.HTTPMethod = "POST"
        request.HTTPBody = httpBody
        
        let task = NSURLSession.sharedSession().dataTaskWithRequest(request) {(data, response, error) in
            println(NSString(data: data, encoding: NSUTF8StringEncoding))
        }
        
        task.resume()
    }


}

