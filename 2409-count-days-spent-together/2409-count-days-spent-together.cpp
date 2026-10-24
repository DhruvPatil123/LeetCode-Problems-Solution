class Solution {
private:
    int getDayOfYear(string date) {
        vector<int> daysInMonths = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = stoi(date.substr(0, 2));
        int day = stoi(date.substr(3, 2));
        
        int totalDays = day;
        for (int i = 0; i < month - 1; ++i) {
            totalDays += daysInMonths[i];
        }
        return totalDays;
    }

public:
    int countDaysTogether(string arriveAlice, string leaveAlice, string arriveBob, string leaveBob) {
        int aliceArrival = getDayOfYear(arriveAlice);
        int aliceLeave = getDayOfYear(leaveAlice);
        int bobArrival = getDayOfYear(arriveBob);
        int bobLeave = getDayOfYear(leaveBob);
        
        int latestArrival = max(aliceArrival, bobArrival);
        int earliestLeave = min(aliceLeave, bobLeave);
        
        if (latestArrival <= earliestLeave) {
            return earliestLeave - latestArrival + 1;
        }
        
        return 0;
    }
};