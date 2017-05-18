package airbnb;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by EricLee on 10/28/16.
 */
public class IP2CIDR {

    public static void main(String[] args) {
        System.out.println(range2cidrlist("255.0.0.7", 10));
    }

    public static List<String> range2cidrlist( String startIp, int range ) {
        // check parameters
        String a = "";
        long start = ipToLong(startIp);
        long end = start + range - 1;

        List<String> result = new ArrayList<String>();
        while (start <= end) {
            // identify the location of first 1's from lower bit to higher bit of start IP
            // e.g. 00000001.00000001.00000001.01101100, return 4 (100)
            long locOfFirstOne = start & (-start);
            int maxMask = 32 - (int) (Math.log(locOfFirstOne) / Math.log(2));

            // calculate how many IP addresses between the start and end
            // e.g. between 1.1.1.111 and 1.1.1.120, there are 10 IP address
            // 3 bits to represent 8 IPs, from 1.1.1.112 to 1.1.1.119 (119 - 112 + 1 = 8)
            double curRange = Math.log(end - start + 1) / Math.log(2);
            int maxDiff = 32 - (int) Math.floor(curRange);

            // why max?
            // if the maxDiff is larger than maxMask
            // which means the numbers of IPs from start to end is smaller than mask range
            // so we can't use as many as bits we want to mask the start IP to avoid exceed the end IP
            // Otherwise, if maxDiff is smaller than maxMask, which means number of IPs is larger than mask range
            // in this case we can use maxMask to mask as many as IPs from start we want.
            maxMask = Math.max(maxDiff, maxMask);

            // Add to results
            String ip = longToIP(start);
            result.add(ip + "/" + maxMask);
            // We have already included 2^(32 - maxMask) numbers of IP into result
            // So the next round start must add that number
            start += Math.pow(2, (32 - maxMask));
        }
        return result;
    }

    private static long ipToLong(String strIP) {
        long[] ip = new long[4];
        String[] ipSec = strIP.split("\\.");
        for (int k = 0; k < 4; k++) {
            ip[k] = Long.valueOf(ipSec[k]);
        }

        return (ip[0] << 24) + (ip[1] << 16) + (ip[2] << 8) + ip[3];
    }

    private static String longToIP(long longIP) {
        StringBuffer sb = new StringBuffer("");
        sb.append(String.valueOf(longIP >>> 24));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x00FFFFFF) >>> 16));
        sb.append(".");
        sb.append(String.valueOf((longIP & 0x0000FFFF) >>> 8));
        sb.append(".");
        sb.append(String.valueOf(longIP & 0x000000FF));

        return sb.toString();
    }
}
