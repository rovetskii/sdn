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
public class teachers {
String first_name;
String surname;
String academic_title;
String degree;
String organization;
String post;
String photo;

    public teachers(String first_name, String surname, String academic_title, String degree, String organization, String post, String photo) {
        this.first_name = first_name;
        this.surname = surname;
        this.academic_title = academic_title;
        this.degree = degree;
        this.organization = organization;
        this.post = post;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "teachers{" + "first_name=" + first_name + ", surname=" + surname + ", academic_title=" + academic_title + ", degree=" + degree + ", organization=" + organization + ", post=" + post + ", photo=" + photo + '}';
    } 
}
