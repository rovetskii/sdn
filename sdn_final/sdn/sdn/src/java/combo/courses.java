/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package combo;

/**
 *
 * @author Ivan
 */
public class courses {
String id;
String name;
String name_video;



    public courses(String name_video) {
        this.name_video = name_video;
    }

    public courses(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "courses{" + "id=" + id + ", name=" + name + ", name_video=" + name_video + '}';
    }
    

  


    
}
