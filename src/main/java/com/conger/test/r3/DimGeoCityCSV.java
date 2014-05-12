package com.conger.test.r3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class DimGeoCityCSV
{

    private static final String REGION = "/home/jj/Documents/NeuLion/dim_geo/region_codes.csv";
    private static final String CITY = "/home/jj/Documents/NeuLion/dim_geo/GeoIPCity-534-Location.csv";
    
    private Map<String, String> regions = Maps.newHashMap();
    private Set<String> cities = Sets.newHashSet();
    
    private static final HashMap<String, Integer> hashmapcountryCodetoindex = new HashMap<String, Integer>(512);
    private static final HashMap<String, Integer> hashmapcountryNametoindex = new HashMap<String, Integer>(512);
    private static final String[] countryCode = {
    "--","AP","EU","AD","AE","AF","AG","AI","AL","AM","AN","AO","AQ","AR",
    "AS","AT","AU","AW","AZ","BA","BB","BD","BE","BF","BG","BH","BI","BJ",
    "BM","BN","BO","BR","BS","BT","BV","BW","BY","BZ","CA","CC","CD","CF",
    "CG","CH","CI","CK","CL","CM","CN","CO","CR","CU","CV","CX","CY","CZ",
    "DE","DJ","DK","DM","DO","DZ","EC","EE","EG","EH","ER","ES","ET","FI",
    "FJ","FK","FM","FO","FR","FX","GA","GB","GD","GE","GF","GH","GI","GL",
    "GM","GN","GP","GQ","GR","GS","GT","GU","GW","GY","HK","HM","HN","HR",
    "HT","HU","ID","IE","IL","IN","IO","IQ","IR","IS","IT","JM","JO","JP",
    "KE","KG","KH","KI","KM","KN","KP","KR","KW","KY","KZ","LA","LB","LC",
    "LI","LK","LR","LS","LT","LU","LV","LY","MA","MC","MD","MG","MH","MK",
    "ML","MM","MN","MO","MP","MQ","MR","MS","MT","MU","MV","MW","MX","MY",
    "MZ","NA","NC","NE","NF","NG","NI","NL","NO","NP","NR","NU","NZ","OM",
    "PA","PE","PF","PG","PH","PK","PL","PM","PN","PR","PS","PT","PW","PY",
    "QA","RE","RO","RU","RW","SA","SB","SC","SD","SE","SG","SH","SI","SJ",
    "SK","SL","SM","SN","SO","SR","ST","SV","SY","SZ","TC","TD","TF","TG",
    "TH","TJ","TK","TM","TN","TO","TL","TR","TT","TV","TW","TZ","UA","UG",
    "UM","US","UY","UZ","VA","VC","VE","VG","VI","VN","VU","WF","WS","YE",
    "YT","RS","ZA","ZM","ME","ZW","A1","A2","O1","AX","GG","IM","JE","BL",
    "MF"};

    private static final String[] countryName = {
    "N/A","Asia/Pacific Region","Europe","Andorra","United Arab Emirates",
    "Afghanistan","Antigua and Barbuda","Anguilla","Albania","Armenia",
    "Netherlands Antilles","Angola","Antarctica","Argentina","American Samoa",
    "Austria","Australia","Aruba","Azerbaijan","Bosnia and Herzegovina",
    "Barbados","Bangladesh","Belgium","Burkina Faso","Bulgaria","Bahrain",
    "Burundi","Benin","Bermuda","Brunei Darussalam","Bolivia","Brazil","Bahamas",
    "Bhutan","Bouvet Island","Botswana","Belarus","Belize","Canada",
    "Cocos (Keeling) Islands","Congo, The Democratic Republic of the",
    "Central African Republic","Congo","Switzerland","Cote D'Ivoire",
    "Cook Islands","Chile","Cameroon","China","Colombia","Costa Rica","Cuba",
    "Cape Verde","Christmas Island","Cyprus","Czech Republic","Germany",
    "Djibouti","Denmark","Dominica","Dominican Republic","Algeria","Ecuador",
    "Estonia","Egypt","Western Sahara","Eritrea","Spain","Ethiopia","Finland",
    "Fiji","Falkland Islands (Malvinas)","Micronesia, Federated States of",
    "Faroe Islands","France","France, Metropolitan","Gabon","United Kingdom",
    "Grenada","Georgia","French Guiana","Ghana","Gibraltar","Greenland","Gambia",
    "Guinea","Guadeloupe","Equatorial Guinea","Greece",
    "South Georgia and the South Sandwich Islands","Guatemala","Guam",
    "Guinea-Bissau","Guyana","Hong Kong","Heard Island and McDonald Islands",
    "Honduras","Croatia","Haiti","Hungary","Indonesia","Ireland","Israel","India",
    "British Indian Ocean Territory","Iraq","Iran, Islamic Republic of",
    "Iceland","Italy","Jamaica","Jordan","Japan","Kenya","Kyrgyzstan","Cambodia",
    "Kiribati","Comoros","Saint Kitts and Nevis",
    "Korea, Democratic People's Republic of","Korea, Republic of","Kuwait",
    "Cayman Islands","Kazakhstan","Lao People's Democratic Republic","Lebanon",
    "Saint Lucia","Liechtenstein","Sri Lanka","Liberia","Lesotho","Lithuania",
    "Luxembourg","Latvia","Libyan Arab Jamahiriya","Morocco","Monaco",
    "Moldova, Republic of","Madagascar","Marshall Islands",
    "Macedonia","Mali","Myanmar","Mongolia",
    "Macau","Northern Mariana Islands","Martinique","Mauritania","Montserrat",
    "Malta","Mauritius","Maldives","Malawi","Mexico","Malaysia","Mozambique",
    "Namibia","New Caledonia","Niger","Norfolk Island","Nigeria","Nicaragua",
    "Netherlands","Norway","Nepal","Nauru","Niue","New Zealand","Oman","Panama",
    "Peru","French Polynesia","Papua New Guinea","Philippines","Pakistan",
    "Poland","Saint Pierre and Miquelon","Pitcairn Islands","Puerto Rico","" +
    "Palestinian Territory","Portugal","Palau","Paraguay","Qatar",
    "Reunion","Romania","Russian Federation","Rwanda","Saudi Arabia",
    "Solomon Islands","Seychelles","Sudan","Sweden","Singapore","Saint Helena",
    "Slovenia","Svalbard and Jan Mayen","Slovakia","Sierra Leone","San Marino",
    "Senegal","Somalia","Suriname","Sao Tome and Principe","El Salvador",
    "Syrian Arab Republic","Swaziland","Turks and Caicos Islands","Chad",
    "French Southern Territories","Togo","Thailand","Tajikistan","Tokelau",
    "Turkmenistan","Tunisia","Tonga","Timor-Leste","Turkey","Trinidad and Tobago",
    "Tuvalu","Taiwan","Tanzania, United Republic of","Ukraine","Uganda",
    "United States Minor Outlying Islands","United States","Uruguay","Uzbekistan",
    "Holy See (Vatican City State)","Saint Vincent and the Grenadines",
    "Venezuela","Virgin Islands, British","Virgin Islands, U.S.","Vietnam",
    "Vanuatu","Wallis and Futuna","Samoa","Yemen","Mayotte","Serbia",
    "South Africa","Zambia","Montenegro","Zimbabwe","Anonymous Proxy",
    "Satellite Provider","Other","Aland Islands","Guernsey","Isle of Man","Jersey",
    "Saint Barthelemy","Saint Martin"};


    static {
        int i;
        if(countryCode.length!=countryName.length)
            throw new AssertionError("countryCode.length!=countryName.length");
              
        for (i = 0; i < countryCode.length ;i++){
            hashmapcountryCodetoindex.put(countryCode[i],Integer.valueOf(i));
            hashmapcountryNametoindex.put(countryName[i],Integer.valueOf(i));
        }
    };
    
    public static void main(String[] args) throws Exception
    {
        DimGeoCityCSV dim = new DimGeoCityCSV();
        dim.loadRegionCode();
        dim.loadCity();
    }
    
    private void loadCity() throws Exception
    {
        Connection connection = this.getConnection();
        PreparedStatement ps = connection.prepareStatement("replace into dim_geo_city (city_id, city_name, region_id, latitude, longitude) values (?,?,?,?,?)");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(CITY), "ISO-8859-1"));
        String line = br.readLine();
        line = br.readLine();
        while ((line = br.readLine()) != null)
        {
            String[] segments = line.split(",");
            if (!segments[2].equals("\"\""))
            {
                String countryCode = removeQuote(segments[1]);
                String regionCode = removeQuote(segments[2]);
                String city = removeQuote(segments[3]);
                double lat = Double.parseDouble(segments[5]);
                double lon = Double.parseDouble(segments[6]);
                String country = countryName[hashmapcountryCodetoindex.get(countryCode)];
                String region = regions.get(countryCode + "-" + regionCode);
                if (region == null)
                {
                    region = "--";
                }
                if ("".equals(city))
                {
                    city = "--";
                }
                if (cities.add(country + "|" + region + "|" + city))
                {
                    ps.setInt(1, (country + "|" + region + "|" + city).hashCode());
                    ps.setString(2, country + "|" + region + "|" + city);
                    ps.setInt(3, (country + "|" + region).hashCode());
                    ps.setDouble(4, lat);
                    ps.setDouble(5, lon);
                    ps.addBatch();
                }
            }
        }
        ps.executeBatch();
        br.close();
    }

    private Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/city?useServerPrepStmts=false&rewriteBatchedStatements=true&useUnicode=true&characterEncoding=UTF-8",
                "root", "111111");
    }
    
    private void loadRegionCode() throws Exception
    {
        BufferedReader br = new BufferedReader(new FileReader(new File(REGION)));
        String line = null;
        while ((line = br.readLine()) != null)
        {
            String[] segments = line.split(",");
            regions.put(segments[0] + "-" + segments[1], removeQuote(segments[2]));
        }
        br.close();
    }
    
    private String removeQuote(String s)
    {
        String segment = s;
        if (segment.startsWith("\""))
        {
            segment = segment.substring(1);
        }
        if (segment.endsWith("\""))
        {
            segment = segment.substring(0, segment.length() - 1);
        }
        return segment;
    }
}
