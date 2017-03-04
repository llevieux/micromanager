/*
 * Copyright 2017 Lucas LeVieux <contact@lucaslevieux.com>.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.lucaslevieux.micromanager;

import javax.swing.JPanel;

/**
 * The panel responsible for asking "are you working?"
 * 
 * @author Lucas LeVieux <contact@lucaslevieux.com>
 */
public class Nag extends JPanel {
    /**
     * The current task being completed.  
     */
    private final String task;
            
    public Nag(String task){
        this.task = task;
    }
}
