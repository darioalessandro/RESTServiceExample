//
//  ViewController.swift
//  iOSClient
//
//  Created by Dario Lencina on 3/26/15.
//  Copyright (c) 2015 dario. All rights reserved.
//

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

