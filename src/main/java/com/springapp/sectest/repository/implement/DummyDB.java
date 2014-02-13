package com.springapp.sectest.repository.implement;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

@Repository
public class DummyDB {
    @Autowired
    private SessionFactory sessionFactory;

    private List<String> countries;
    private List<String> tags;

    public DummyDB() {

        String data = "Afghanistan, Albania, Algeria, Andorra, Angola, Antigua & Deps,"+
                "United Kingdom,United States,Uruguay,Uzbekistan,Vanuatu,Vatican City,Venezuela,Vietnam,Yemen,Zambia,Zimbabwe";

        countries = new ArrayList<String>();
        StringTokenizer st = new StringTokenizer(data, ",");

        //Parse the country CSV list and set as Array
        while(st.hasMoreTokens()) {
            countries.add(st.nextToken().trim());
        }

        String strTags = "SharePoint, Spring, Struts, Java, JQuery, ASP, PHP, JavaScript, MySQL, ASP, .NET";
        tags = new ArrayList<String>();
        StringTokenizer st2 = new StringTokenizer(strTags, ",");

        //Parse the tags CSV list and set as Array
        while(st2.hasMoreTokens()) {
            tags.add(st2.nextToken().trim());
        }

    }

    public List<String> getCountryList(String query) {

        String country = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i < countries.size(); i++) {
            country = countries.get(i).toLowerCase();
            if(country.startsWith(query)) {
                matched.add(countries.get(i));
            }
        }
        return matched;
    }

    public List<String> getTechList(String query) {
        String country = null;
        query = query.toLowerCase();
        List<String> matched = new ArrayList<String>();
        for(int i=0; i < tags.size(); i++) {
            country = tags.get(i).toLowerCase();
            if(country.startsWith(query)) {
                matched.add(tags.get(i));
            }
        }
        return matched;
    }

    public List<String> getDBCountry(String query) {
        List<String> countries = new ArrayList<String>();

        Query query1 = sessionFactory.getCurrentSession()
                .createSQLQuery("select * from countries where name like '%:countryName%'").setParameter("countryName",query);
        countries = query1.list();

        return countries;
    }
}
