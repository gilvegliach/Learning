//
//  mainTVC.swift
//  TableVCTest
//
//  Created by Gil on 22/11/14.
//  Copyright (c) 2014 Gil. All rights reserved.
//

import UIKit

class mainTVC: UITableViewController {
    var data = [String]()
    
    override func tableView(tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    
    // Row display. Implementers should *always* try to reuse cells by setting each cell's reuseIdentifier and querying for available reusable cells with dequeueReusableCellWithIdentifier:
    // Cell gets various attributes set automatically based on table (separators) and data source (accessory views, editing controls)
    
    override func tableView(tableView: UITableView, cellForRowAtIndexPath indexPath: NSIndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCellWithIdentifier("Cell") as UITableViewCell
        cell.textLabel.text = data[indexPath.row]
        
        return cell
    }
    
    @IBAction func addNewData(sender: UIBarButtonItem) {
        var alert = UIAlertController(title: "Input", message: "Input something", preferredStyle: .Alert)
        alert.addTextFieldWithConfigurationHandler() {
            textField in
            textField.textAlignment = .Right
            textField.placeholder = "New element"
            
        }
        
        alert.addAction(UIAlertAction(title: "Ok", style: .Default, handler: {
            action in
            self.data.append((alert.textFields[0] as UITextField).text)
            self.tableView.reloadData()
        }))
        
        alert.addAction(UIAlertAction(title: "Cancel", style: .Cancel, handler: nil))
        presentViewController(alert, animated: true, completion: nil)
    }
    

}
