import java.util.*;
import java.util.stream.*;

public class TubeStations {
  static List<String> stations = new ArrayList<>();
  static {
    stations.add("Acton Town");
    stations.add("Acton Central");
    stations.add("Acton Central");
    stations.add("Aldgate");
    stations.add("Aldgate East");
    stations.add("Alperton");
    stations.add("Amersham");
    stations.add("Angel");
    stations.add("Archway");
    stations.add("Arnos Grove");
    stations.add("Arsenal");
    stations.add("Baker Street");
    stations.add("Baker Street");
    stations.add("Balham");
    stations.add("Bank");
    stations.add("Barbican");
    stations.add("Barking");
    stations.add("Barkingside");
    stations.add("Barons Court");
    stations.add("Bayswater");
    stations.add("Becontree");
    stations.add("Belsize Park");
    stations.add("Bermondsey");
    stations.add("Bethnal Green");
    stations.add("Blackfriars");
    stations.add("Blackhorse Road");
    stations.add("Bond Street");
    stations.add("Borough");
    stations.add("Boston Manor");
    stations.add("Bounds Green");
    stations.add("Bow Road");
    stations.add("Brent Cross");
    stations.add("Brixton");
    stations.add("Bromley by Bow");
    stations.add("Brondesbury");
    stations.add("Brondesbury Park");
    stations.add("Buckhurst Hill");
    stations.add("Burnt Oak");
    stations.add("Caledonian Road");
    stations.add("Caledonian Road   Barnesbury");
    stations.add("Camden Road");
    stations.add("Camden Town");
    stations.add("Canada Water");
    stations.add("Canary Wharf");
    stations.add("Cannon Street");
    stations.add("Canonbury");
    stations.add("Canons Park");
    stations.add("Chalfont   Latimer");
    stations.add("Chalk Farm");
    stations.add("Chancery Lane");
    stations.add("Charing Cross");
    stations.add("Chesham");
    stations.add("Chigwell");
    stations.add("Chiswick Park");
    stations.add("Chorleywood");
    stations.add("City Thameslink");
    stations.add("Clapham Common");
    stations.add("Clapham North");
    stations.add("Clapham South");
    stations.add("Cockfosters");
    stations.add("Colindale");
    stations.add("Colliers Wood");
    stations.add("Covent Garden");
    stations.add("Croxley");
    stations.add("Dagenham East");
    stations.add("Dagenham Heathway");
    stations.add("Dalston Kingsland");
    stations.add("Debden");
    stations.add("Dollis Hill");
    stations.add("Ealing Broadway");
    stations.add("Ealing Common");
    stations.add("Earl s Court");
    stations.add("East Acton");
    stations.add("East Finchley");
    stations.add("East Ham");
    stations.add("East Putney");
    stations.add("Eastcote");
    stations.add("Edgware");
    stations.add("Edgware Road Bakerloo Line");
    stations.add("Edgware Road Circle Line");
    stations.add("Elephant   Castle");
    stations.add("Elm Park");
    stations.add("Embankment");
    stations.add("Epping");
    stations.add("Euston");
    stations.add("Euston Square");
    stations.add("Fairlop");
    stations.add("Farringdon");
    stations.add("Farringdon");
    stations.add("Finchley Central");
    stations.add("Finchley Road");
    stations.add("Finchley Road and Frognal");
    stations.add("Finsbury Park");
    stations.add("Fulham Broadway");
    stations.add("Gants Hill");
    stations.add("Gloucester Road");
    stations.add("Golders Green");
    stations.add("Goldhawk Road");
    stations.add("Goodge Street");
    stations.add("Gospel Oak");
    stations.add("Grange Hill");
    stations.add("Great Portland Street");
    stations.add("Greenford");
    stations.add("Gunnersbury");
    stations.add("Green Park");
    stations.add("Hackney Central");
    stations.add("Hackney Wick");
    stations.add("Hainault");
    stations.add("Hammersmith Dist Picc Line");
    stations.add("Hammersmith H C Line");
    stations.add("Hampstead");
    stations.add("Hampstead Heath");
    stations.add("Hanger Lane");
    stations.add("Harlesden");
    stations.add("Harrow Wealdstone");
    stations.add("Harrow on the Hill");
    stations.add("Hatton Cross");
    stations.add("Heathrow Terminals");
    stations.add("Heathrow Terminal");
    stations.add("Hendon Central");
    stations.add("High Barnet");
    stations.add("High Street Kensington");
    stations.add("Highbury Islington");
    stations.add("Highgate");
    stations.add("Hillingdon");
    stations.add("Holborn");
    stations.add("Holland Park");
    stations.add("Holloway Road");
    stations.add("Homerton");
    stations.add("Hornchurch");
    stations.add("Hounslow Central");
    stations.add("Hounslow East");
    stations.add("Hounslow West");
    stations.add("Hyde Park Corner");
    stations.add("Ickenham");
    stations.add("Kennington");
    stations.add("Kensal Green");
    stations.add("Kensal Rise");
    stations.add("Kensington Olympia");
    stations.add("Kentish Town");
    stations.add("Kentish Town West");
    stations.add("Kenton");
    stations.add("Kew Gardens");
    stations.add("Kilburn");
    stations.add("Kilburn Park");
    stations.add("King s Cross St Pancras");
    stations.add("King s Cross Thameslink");
    stations.add("Kingsbury");
    stations.add("Knightsbridge");
    stations.add("Ladbroke Grove");
    stations.add("Lambeth North");
    stations.add("Lancaster Gate");
    stations.add("Latimer Road");
    stations.add("Leicester Square");
    stations.add("Leyton");
    stations.add("Leytonstone");
    stations.add("Liverpool Street");
    stations.add("London Bridge");
    stations.add("Loughton");
    stations.add("Maida Vale");
    stations.add("Manor House");
    stations.add("Mansion House");
    stations.add("Marble Arch");
    stations.add("Marylebone");
    stations.add("Mile End");
    stations.add("Mill Hill East");
    stations.add("Monument");
    stations.add("Moor Park");
    stations.add("Moorgate");
    stations.add("Morden");
    stations.add("Mornington Crescent");
    stations.add("Neasden");
    stations.add("New Cross");
    stations.add("New Cross Gate");
    stations.add("Newbury Park");
    stations.add("North Acton");
    stations.add("North Ealing");
    stations.add("North Greenwich");
    stations.add("North Harrow");
    stations.add("North Wembley");
    stations.add("Northfields");
    stations.add("Northolt");
    stations.add("Northolt");
    stations.add("Northwick Park");
    stations.add("Northwood");
    stations.add("Northwood Hills");
    stations.add("Notting Hill Gate");
    stations.add("Oakwood");
    stations.add("Old Street");
    stations.add("Osterley");
    stations.add("Oval");
    stations.add("Oxford Circus");
    stations.add("Paddington");
    stations.add("Paddington H C Line");
    stations.add("Park Royal");
    stations.add("Parsons Green");
    stations.add("Perivale");
    stations.add("Piccadilly Circus");
    stations.add("Pimlico");
    stations.add("Pinner");
    stations.add("Plaistow");
    stations.add("Preston Road");
    stations.add("Putney Bridge");
    stations.add("Queen s Park");
    stations.add("Queensbury");
    stations.add("Queensway");
    stations.add("Ravenscourt Park");
    stations.add("Rayners Lane");
    stations.add("Redbridge");
    stations.add("Regent s Park");
    stations.add("Richmond");
    stations.add("Richmond");
    stations.add("Rickmansworth");
    stations.add("Roding Valley");
    stations.add("Rotherhithe");
    stations.add("Royal Oak");
    stations.add("Ruislip");
    stations.add("Ruislip Gardens");
    stations.add("Ruislip Manor");
    stations.add("Russell Square");
    stations.add("St James s Park");
    stations.add("Seven Sisters");
    stations.add("Shadwell");
    stations.add("Shepherd s Bush");
    stations.add("Shepherd s Bush Market");
    stations.add("Sloane Square");
    stations.add("Snaresbrook");
    stations.add("South Acton");
    stations.add("South Ealing");
    stations.add("South Harrow");
    stations.add("South Kensington");
    stations.add("South Kenton");
    stations.add("South Ruislip");
    stations.add("South Wimbledon");
    stations.add("South Woodford");
    stations.add("Southfields");
    stations.add("Southgate");
    stations.add("Southwark");
    stations.add("St John s Wood");
    stations.add("St Paul s");
    stations.add("Stamford Brook");
    stations.add("Stanmore");
    stations.add("Stepney Green");
    stations.add("Stockwell");
    stations.add("Stonebridge Park");
    stations.add("Stratford");
    stations.add("Stratford");
    stations.add("Sudbury Hill");
    stations.add("Sudbury Town");
    stations.add("Surrey Quays");
    stations.add("Swiss Cottage");
    stations.add("Temple");
    stations.add("Theydon Bois");
    stations.add("Tooting Bec");
    stations.add("Tooting Broadway");
    stations.add("Tottenham Court Road");
    stations.add("Tottenham Court Road");
    stations.add("Tottenham Hale");
    stations.add("Totteridge   Whetstone");
    stations.add("Tower Hill");
    stations.add("Tufnell Park");
    stations.add("Turnham Green");
    stations.add("Turnpike Lane");
    stations.add("Upminster");
    stations.add("Upminster Bridge");
    stations.add("Upney");
    stations.add("Upton Park");
    stations.add("Uxbridge");
    stations.add("Vauxhall");
    stations.add("Victoria");
    stations.add("Walthamstow Central");
    stations.add("Wanstead");
    stations.add("Wapping");
    stations.add("Warren Street");
    stations.add("Warwick Avenue");
    stations.add("Waterloo");
    stations.add("Watford");
    stations.add("Wembley Central");
    stations.add("Wembley Park");
    stations.add("West Acton");
    stations.add("West Brompton");
    stations.add("West Finchley");
    stations.add("West Ham");
    stations.add("West Hampstead");
    stations.add("West Hampstead");
    stations.add("West Hampstead");
    stations.add("West Hampstead");
    stations.add("West Harrow");
    stations.add("West Kensington");
    stations.add("West Ruislip");
    stations.add("Westbourne Park");
    stations.add("Westminster");
    stations.add("White City");
    stations.add("Whitechapel");
    stations.add("Willesden Green");
    stations.add("Willesden Junction");
    stations.add("Willesden Junction");
    stations.add("Wimbledon");
    stations.add("Wimbledon Park");
    stations.add("Wood Green");
    stations.add("Woodford");
    stations.add("Woodside Park");
  }

  static final int ALL = mask("abcdefghikjlmnopqrstuvwxyz");

  public static void main(String[] args) {
    Map<Integer, String> intsToStation = new HashMap<>();
    for (String station : stations) {
      intsToStation.put(mask(station), station);
    }
    System.out.println(stations.size());
    stations = new ArrayList<>(intsToStation.values());
    System.out.println(stations.size());
    Map<String, Integer> stationsToInt = new HashMap<>();
    for (String station : stations) {
      stationsToInt.put(station, mask(station));
    }
    System.out.println(solve3(stations, stationsToInt, 0, 0, 0, 5, new String[10]));
  }

  // [St James s Park, Vauxhall, Finchley Road and Frognal, Belsize Park, Queensway]
  static int solve3(List<String> stations, Map<String, Integer> stationsToInt,
      int selected, int acc, int j, int min, String[] result) {
    if (selected == ALL) {
      System.out.println("SZ: " + acc);
      System.out.println("RS: " + Arrays.toString(result));
      return acc;
    }
    if (acc > min) {
      return min;
    }
    int res = stations.size();
    if (j >= res) {
      return res;
    }
    String station = stations.get(j);
    int newSelected = selected | stationsToInt.get(station);
    if (newSelected != selected && acc + 1 <= min) {
      result[acc] = station;
      res = solve3(stations, stationsToInt, newSelected, acc + 1, j + 1, min, result);
    }
    return Math.min(res, solve3(stations, stationsToInt, selected, acc, j + 1, Math.min(res, min), result));
  }

  static int solve2(List<String> stations, Map<String, Integer> stationsToInt,
      int selected, int acc, int j, int min) {
    if (selected == ALL) {
      System.out.println("A: " + acc);
      return acc;
    }
    if (acc >= min) {
      return min;
    }
    int res = stations.size();
    if (j >= res) {
      return res;
    }
    String station = stations.get(j);
    int newSelected = selected | stationsToInt.get(station);
    if (newSelected != selected) {
      res = solve2(stations, stationsToInt, newSelected, acc + 1, j + 1, min);
    }
    return Math.min(res, solve2(stations, stationsToInt, selected, acc, j + 1, Math.min(res, min)));
  }

  static int mask(String s) {
    int mask = 0;
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == ' ') continue;
      int shift = Character.toLowerCase(c) - 'a';
      mask |= 1 << shift;
    }
    return mask;
  }

  static Set<String> solve(Set<String> stations) {
   if (stations.isEmpty()) return stations;
   Set<String> result = stations;
   for (String station : stations) {
    Set<String> subStations = new HashSet<>(stations);
    subStations.remove(station);
    Set<String> subResult = solve(subStations);
    if (!cover(subResult, station)) {
      subResult.add(station);
    }
    if (subResult.size() < result.size()) {
      result = subResult;
    }
   }
   return result;
  }

  static boolean cover(Set<String> set, String s) {
    Set<Integer> setInt = set.stream()
      .flatMap(st -> st.chars().mapToObj(c -> (Integer) Character.toLowerCase(c)))
      .collect(Collectors.toSet());
    return s.chars().allMatch(setInt::contains);
  }
}
