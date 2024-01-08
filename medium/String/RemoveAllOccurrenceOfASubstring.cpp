#include <iostream>
#include <string>

using namespace std;

class Solution {
public:
    string removeOccurrences(string s, string part) {
        string res = s;
        int i, j; // i ietrates s, while j iterates x
        int partLen = part.size();
        int strLen = s.size();
        
        for(i = 0, j = 0; i< strLen; i++){
            res[j++] = s[i];
            if(j >= partLen && res.substr(j-partLen, partLen) == part){
                j -= partLen;
            }
        }
        return res.substr(0,j);
    }
};

int main(){


    return 0;
}