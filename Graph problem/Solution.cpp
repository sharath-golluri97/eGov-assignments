#include "bits/stdc++.h"

using namespace std;
typedef long long int lli;
#define pb push_back
#define mp make_pair
#define mod 1000000007
#define inf 10000000007
#define ff first
#define ss second
#define endl "\n"
#define IOS ios_base::sync_with_stdio(0);cin.tie(0);cout.tie(0);
lli multiply(lli a,lli b){return (a%mod * b%mod)%mod;}
lli add(lli a,lli b){return (a%mod + b%mod)%mod;}

//CODE STARTS HERE

map<int,vector<int> > hashMap;
map<int,int> indegree;
void ERR(){
    cout<<"There is input error from file"<<endl;
}

int trim(string s){
    int i = 0;
    while(s[i] == ' '){
        i++;
    }
    int node = 0;
    while(i < s.size()){
        node = node*10 + (s[i]-'0');
        i++;
    }
    return node;
}

void insert(string fromString,string toString){
    int from = trim(fromString);
    int to = trim(toString);
    hashMap[from].pb(to);
    indegree[to]++;
}

void split(string line){
    string delimiter = ",";
    size_t pos = 0;
    pos = line.find(delimiter);
    if(pos != string::npos){
        insert(line.substr(0,pos),line.substr(pos+1,string::npos));
    }
    else{
        ERR();
    }
}

void print(){
    for(map<int,vector<int> > :: iterator it = hashMap.begin();it != hashMap.end();it++){
        cout<<it->first<<" - >  ";
        vector<int> v = it->second;
        for(int i = 0;i<v.size() - 1;i++){
            cout<<v[i]<<" , ";
        }
        cout<<v.back(); 
        cout<<endl;
    }

}

void dfs(vector<int> path){
    int prerequisite = path.back();
    vector<int> list = hashMap[prerequisite];
    if(list.size() == 0){
        for(int i=0;i<path.size()-1;i++){
            cout<<path[i]<<" -> ";
        }
        cout<<path.back()<<endl;
        return;
    }
    for(int i=0;i<list.size();i++){
        path.pb(list[i]);
        dfs(path);
        path.pop_back();
    }
}

int main(){
    IOS
    ifstream myfile ("input.txt");
    string line;
    int nodes;
    getline(myfile,line);
    nodes = stoi(line);
    for(int i=0;i<nodes;i++){
        indegree[i] = 0;
    }

    if(myfile.is_open()){
        while(getline(myfile,line)){
            split(line);
        }
        myfile.close();
    }
    // print();
    
    for(int i=0;i<nodes;i++){
        if(indegree[i] != 0){
            continue;
        }
        vector<int> path;
        path.pb(i);
        dfs(path);
    }
    return 0;
}