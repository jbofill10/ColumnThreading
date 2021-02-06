package edu.frostburg.cosc444;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Thread class that does several operations on the data set
 */
public class ColumnReader implements Runnable {

    @Override
    public void run() {
        if (JavaApplication.districtVictims.tryLock())
            districtVictimCount();
        else if (JavaApplication.streetVictims.tryLock())
                streetVictimCount();
        else if (JavaApplication.partOfDayVictims.tryLock())
                    victimPartOfDayCount();
        else if (JavaApplication.monthVictims.tryLock())
                        monthVictimCount();
        else if (JavaApplication.weekdayVictims.tryLock())
                            weekdayVictimCount();
        else if (JavaApplication.neighborhoodVictims.tryLock())
                                neighborhoodVictimCount();
        else if (JavaApplication.weeklyPartOfDay.tryLock())
                                    weekdayPartOfDayVictimCount();
        else if (JavaApplication.allRows.tryLock()) 
            if (JavaApplication.choice.toUpperCase().equals("Y"))
                getAllRows();
    }


    /**
     * Gets the count of victims per district
     */
    public void districtVictimCount(){
        ArrayList<String> districts = getDistrict();
        ArrayList<Integer> victims = getVictims();

        HashMap<String, Integer> districtVictims = new HashMap<>();

        StringBuilder result = new StringBuilder();

        if(districts != null && victims != null) {
            for (int i = 0; i < districts.size(); i++) {
                if(districtVictims.containsKey(districts.get(i)) && victims.get(i) > 0)
                    districtVictims.replace(districts.get(i), districtVictims.get(districts.get(i)) + victims.get(i));
                else if(!districtVictims.containsKey(districts.get(i)) && victims.get(i) > 0)
                    districtVictims.put(districts.get(i), victims.get(i));
            }
            boolean printed = false;
            for(Map.Entry<String,Integer> entry : districtVictims.entrySet()) {
                if (!printed) {
                    result.append("Printing out victim count to their respective Districts:\n");
                    printed = true;
                }
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);

        }
    }

    /**
     * Gets the count of victims on the specific streets of each district
     */
    public void streetVictimCount(){
        /**
         * Possible deadlock here since districtVictimCount needs getVictims() and getDistrict() as does this method
         */
        ArrayList<String> streets = getStreet();
        ArrayList<Integer> victims = getVictims();
        ArrayList<String> districts = getDistrict();

        HashMap<String, Integer> districtVictims = new HashMap<>();

        StringBuilder result = new StringBuilder();

        if(streets != null && victims != null) {
            for (int i = 0; i < streets.size(); i++) {
                if(districtVictims.containsKey(districts.get(i) +", "+streets.get(i)) && victims.get(i) > 0)
                    districtVictims.replace(districts.get(i) +", "+streets.get(i), districtVictims.get(districts.get(i) +", "+streets.get(i)) + victims.get(i));
                else if(!districtVictims.containsKey(districts.get(i) +", "+streets.get(i)) && victims.get(i) > 0)
                    districtVictims.put(districts.get(i) +", "+streets.get(i), victims.get(i));
            }
            System.out.println();
            boolean printed = false;
            for(Map.Entry<String,Integer> entry : districtVictims.entrySet()) {
                if (!printed) {
                    result.append("Printing out victim count to their respective districts and streets:\n");
                    printed = true;
                }
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);

        }
    }

    /**
     * Gets the count of victims during the different parts of day
     */
    public void victimPartOfDayCount(){
        ArrayList<String> partOfDay = getPartOfDay();
        ArrayList<Integer> victims = getVictims();

        HashMap<String, Integer> districtVictims = new HashMap<>();

        StringBuilder result = new StringBuilder();

        if(partOfDay != null && victims != null) {
            for (int i = 0; i < partOfDay.size(); i++) {
                if(districtVictims.containsKey(partOfDay.get(i)) && victims.get(i) > 0)
                    districtVictims.replace(partOfDay.get(i), districtVictims.get(partOfDay.get(i)) + victims.get(i));
                else if(!districtVictims.containsKey(partOfDay.get(i)) && victims.get(i) > 0)
                    districtVictims.put(partOfDay.get(i), victims.get(i));
            }
            System.out.println();
            boolean printed = false;
            for(Map.Entry<String,Integer> entry : districtVictims.entrySet()) {
                if (!printed) {
                    result.append("Printing out victim count to their respective parts of day:\n");
                    printed = true;
                }
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);

        }
    }

    /**
     * Gets the count of victims per month
     */
    public void monthVictimCount(){
        ArrayList<String> months = getMonths();
        ArrayList<Integer> victims = getVictims();

        HashMap<String, Integer> districtVictims = new HashMap<>();

        StringBuilder result = new StringBuilder();

        if(months != null && victims != null) {
            for (int i = 0; i < months.size(); i++) {
                if(districtVictims.containsKey(months.get(i)) && victims.get(i) > 0)
                    districtVictims.replace(months.get(i), districtVictims.get(months.get(i)) + victims.get(i));
                else if(!districtVictims.containsKey(months.get(i)) && victims.get(i) > 0)
                    districtVictims.put(months.get(i), victims.get(i));
            }
            System.out.println();
            boolean printed = false;
            for(Map.Entry<String,Integer> entry : districtVictims.entrySet()) {
                if (!printed) {
                    result.append("Printing out victim count to their respective months:\n");
                    printed = true;
                }
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);

        }
    }

    /**
     * Gets the count of victims during each weekday
     */
    public void weekdayVictimCount() {
        ArrayList<String> weekdays = getWeekday();
        ArrayList<Integer> victims = getVictims();

        HashMap<String, Integer> districtVictims = new HashMap<>();

        StringBuilder result = new StringBuilder();

        if (weekdays != null && victims != null) {
            for (int i = 0; i < weekdays.size(); i++) {
                if (districtVictims.containsKey(weekdays.get(i)) && victims.get(i) > 0)
                    districtVictims.replace(weekdays.get(i), districtVictims.get(weekdays.get(i)) + victims.get(i));
                else if (!districtVictims.containsKey(weekdays.get(i)) && victims.get(i) > 0)
                    districtVictims.put(weekdays.get(i), victims.get(i));
            }
            System.out.println();
            boolean printed = false;
            for (Map.Entry<String, Integer> entry : districtVictims.entrySet()) {
                if (!printed) {
                    result.append("Printing out victim count to their respective weekdays:\n");
                    printed = true;
                }
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);
        }
    }

    /**
     * Gets the count of victims in each neighborhood
     */
    public void neighborhoodVictimCount(){
        ArrayList<String> neighborhoods = getNeighborhood();
        ArrayList<Integer> victims = getVictims();

        HashMap<String, Integer> districtVictims = new HashMap<>();

        StringBuilder result = new StringBuilder();

        if (neighborhoods != null && victims != null) {
            for (int i = 0; i < neighborhoods.size(); i++) {
                if (districtVictims.containsKey(neighborhoods.get(i)) && victims.get(i) > 0)
                    districtVictims.replace(neighborhoods.get(i), districtVictims.get(neighborhoods.get(i)) + victims.get(i));
                else if (!districtVictims.containsKey(neighborhoods.get(i)) && victims.get(i) > 0)
                    districtVictims.put(neighborhoods.get(i), victims.get(i));
            }
            System.out.println();
            boolean printed = false;
            for (Map.Entry<String, Integer> entry : districtVictims.entrySet()) {
                if (!printed) {
                    result.append("Printing out victim count to their respective neighborhoods:\n");
                    printed = true;
                }
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);
        }
    }

    /**
     * Gets the count of victims during each weekday during each part of the day
     */
    public void weekdayPartOfDayVictimCount(){
        ArrayList<String> weekdays = getWeekday();
        ArrayList<String> partOfDay = getPartOfDay();
        ArrayList<Integer> victims = getVictims();

        HashMap<String, Integer> districtVictims = new HashMap<>();

        StringBuilder result = new StringBuilder();

        if (weekdays != null && victims != null && partOfDay != null) {
            for (int i = 0; i < weekdays.size(); i++) {
                if (districtVictims.containsKey(weekdays.get(i)+", "+partOfDay.get(i)) && victims.get(i) > 0)
                    districtVictims.replace(weekdays.get(i)+", "+partOfDay.get(i), districtVictims.get(weekdays.get(i)+", "+partOfDay.get(i)) + victims.get(i));
                else if (!districtVictims.containsKey(weekdays.get(i)+", "+partOfDay.get(i)) && victims.get(i) > 0)
                    districtVictims.put(weekdays.get(i)+", "+partOfDay.get(i), victims.get(i));
            }
            System.out.println();
            boolean printed = false;
            for (Map.Entry<String, Integer> entry : districtVictims.entrySet()) {
                if (!printed) {
                    result.append("Printing out victim count to their respective parts of day during the week:\n");
                    printed = true;
                }
                result.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
            }
            System.out.println(result);
        }
    }

    /**
     * Gets all the rows from the data set
     */
    public void getAllRows(){
        ArrayList<String> id = getID();
        ArrayList<String> districts = getDistrict();
        ArrayList<String> neighborhood = getNeighborhood();
        ArrayList<String> weekday = getWeekday();
        ArrayList<String> months = getMonths();
        ArrayList<String> partOfDay = getPartOfDay();
        ArrayList<Integer> victims = getVictims();
        ArrayList<String> street = getStreet();

        StringBuilder result = new StringBuilder();

        result.append("Getting all rows from the data set:\n");
        for(int i = 0; i < id.size(); i++){
            result.append(String.format("%-10s " +
                    "| %-20s " +
                    "| %-40s " +
                    "| %-40s " +
                    "| %-10s " +
                    "| %-10s " +
                    "| %-10s " +
                    "| %-10s\n",
                        id.get(i),
                        districts.get(i),
                        neighborhood.get(i),
                        street.get(i),
                        weekday.get(i),
                        months.get(i),
                        partOfDay.get(i),
                        victims.get(i)));
        }

        System.out.println(result);
    }

    /**
     * Gets values from ID column
     * @return an arraylist of strings with the IDs
     */
    public ArrayList<String> getID(){
        try {
            if(JavaApplication.idLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<String> id = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        id.add(r.getId());
                    }
                    return id;
                }finally {
                    JavaApplication.idLock.unlock();
                }
            }else{
                System.out.println("Could not acquire id-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets values from district column
     * @return returns arraylist of strings that contain each district
     */
    public ArrayList<String> getDistrict(){
        try {
            if(JavaApplication.districtLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<String> districts = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        districts.add(r.getDistrictName());
                    }
                    return districts;
                }finally {
                    JavaApplication.districtLock.unlock();
                }
            }else{
                System.out.println("Could not acquire district-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets values from neighborhood column
     * @return returns arraylist of strings that contain each neighborhood
     */
    public ArrayList<String> getNeighborhood(){
        try {
            if(JavaApplication.neighborhoodLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<String> neighborhoods = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        neighborhoods.add(r.getNeighborhood());
                    }
                    return neighborhoods;
                }finally {
                    JavaApplication.neighborhoodLock.unlock();
                }
            }else{
                System.out.println("Could not acquire neighborhood-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets values from weekday column
     * @return returns arraylist of strings that contain each weekday
     */
    public ArrayList<String> getWeekday(){
        try {
            if(JavaApplication.weekdayLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<String> weekdays = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        weekdays.add(r.getWeekday());
                    }
                    return weekdays;
                }finally {
                    JavaApplication.weekdayLock.unlock();
                }
            }else{
                System.out.println("Could not acquire weekday-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets values from month column
     * @return returns arraylist of strings that contain each month
     */
    public ArrayList<String> getMonths(){
        try {
            if(JavaApplication.monthLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<String> months = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        months.add(r.getMonth());
                    }
                    return months;
                }finally {
                    JavaApplication.monthLock.unlock();
                }
            }else{
                System.out.println("Could not acquire month-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets values from part_of_day column
     * @return returns arraylist of strings that contain each part of day
     */
    public ArrayList<String> getPartOfDay(){
        try {
            if(JavaApplication.partOfDayLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<String> partOfDay = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        partOfDay.add(r.getPartOfDay());
                    }
                    return partOfDay;
                }finally {
                    JavaApplication.partOfDayLock.unlock();
                }
            }else{
                System.out.println("Could not acquire partOfDay-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets values from victims column
     * @return returns arraylist of integers that contain each victim count
     */
    public ArrayList<Integer> getVictims(){
        try {
            if(JavaApplication.victimsLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<Integer> victims = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        victims.add(r.getVictims());
                    }
                    return victims;
                }finally {
                    JavaApplication.victimsLock.unlock();
                }
            }else{
                System.out.println("Could not acquire victim-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Gets values from street column
     * @return returns arraylist of strings that contain each street
     */
    public ArrayList<String> getStreet(){
        try {
            if(JavaApplication.streetLock.tryLock(10, TimeUnit.SECONDS)) {
                try {
                    ArrayList<String> street = new ArrayList<>();

                    for (Record r : JavaApplication.records) {
                        street.add(r.getStreet());
                    }
                    return street;
                }finally {
                    JavaApplication.streetLock.unlock();
                }
            }else{
                System.out.println("Could not acquire district-lock");

                return null;
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }



}

