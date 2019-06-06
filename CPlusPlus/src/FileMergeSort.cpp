#include <iostream>
#include <fstream>
#include <string>
#include <cstdlib>
#include <cstdio>
#include <ctime>

using namespace std;

void initRand() {
    srand(static_cast<unsigned int>(time(0)));
}

void generateRandomFile(string fileName, int numberOfValues, int maxValue) {
    ofstream fout(fileName); // поток записи в файл
    for (int i = 0; i < numberOfValues; i++) {
        int val = rand() % maxValue;
        fout << val << endl;
    }
    fout.flush();
    fout.close();
}

void printFile(string fileName, int limit) {
    ifstream f(fileName); // поток чтения из файла
    int value;
    int i = 0;
    while (i < limit && f >> value) {
        cout << value << " ";
        i++;
    }
    if (i == limit)
        cout << "...";
    cout << endl << endl;
}

// генерирует имя файла блока
string generateBlockFileName(int blockNum) {
    return "b_" + to_string(blockNum);
}

// генерирует имя файла слияния
string generateMergeFileName(int num) {
    return "m_" + to_string(num);
}

// создаем блоки и записываем в них возрастающие подпоследовательности
int writeBlockFiles(string fileName) {
    
    ifstream fin(fileName); // поток на чтение исходного файла
    ofstream fout;          // поток на запись блоков
    
    int value;
    int prevValue;
    int blockNum = 0;
    
    bool firstBlock = true;
    
    while (fin >> value) {
        if (firstBlock) { // сюда заходим только на самом первом шаге цикла, чтобы открыть первый блок на запись
            blockNum++;
            string blockFileName = generateBlockFileName(blockNum);
            fout.open(blockFileName);
            fout << value;
            fout << endl;
            prevValue = value;
            firstBlock = false;
        }
        else if (prevValue <= value) { // возрастающая подпосл. продолжается
            fout << value;
            fout << endl;
            prevValue = value;
        }
        else { // возрастающая подпосл. закончилась, закрываем блок, начинаем новый блок
            fout.flush();
            fout.close();
            blockNum++;
            string blockFileName = generateBlockFileName(blockNum);
            fout.open(blockFileName);
            fout << value;
            fout << endl;
            prevValue = value;
        }
    }
    
    // пишем и закрываем последний файл
    fout.flush();
    fout.close();
    fin.close();
    
    return blockNum;
}

// слияние двух отсортированных файлов
string mergeFiles(string fileName1, string fileName2, int mergeNum) {
    
    string outputFileName = generateMergeFileName(mergeNum);
    ofstream fout(outputFileName); // поток записи
    
    ifstream fin1(fileName1); // открываем на чтение
    ifstream fin2(fileName2);
    
    int val1, val2;
    
    fin1 >> val1;
    fin2 >> val2;
    
    // 1 3 6 8 9 13
    // 2 3 5 9 15
    // 1 2 3 3 5 6 8 9 9 13 15
    
    while (!fin1.eof() && !fin2.eof()) { // идем пока оба файла не закончились
        
        if (val1 < val2) {
            fout << val1 << endl;
            fin1 >> val1;
        }
        else if (val1 > val2) {
            fout << val2 << endl;
            fin2 >> val2;
        }
        else {
            fout << val1 << endl;
            fout << val2 << endl;
            fin1 >> val1;
            fin2 >> val2;
        }
    }
    if (fin1.eof() && !fin2.eof()) {
        fout << val2 << endl;
        while (fin2 >> val2)
            fout << val2 << endl;
    }
    else if (fin2.eof() && !fin1.eof()) {
        fout << val1 << endl;
        while (fin1 >> val1)
            fout << val1 << endl;
    }
    
    fin1.close();
    fin2.close();
    fout.flush();
    fout.close();
    
    return outputFileName;
}

string fileMergeSort(string fileName) {
    
    int numberOfBlocks = writeBlockFiles(fileName);
    
    string mergedFileName = generateBlockFileName(1);
    for (int i = 2; i <= numberOfBlocks; i++) {
        string nextBlockFileName = generateBlockFileName(i);
        string newMergedFileName = mergeFiles(mergedFileName, nextBlockFileName, i - 1);
        remove(nextBlockFileName.c_str()); // удаление предыдущих файлов
        remove(mergedFileName.c_str());
        mergedFileName = newMergedFileName;
    }
    
    string sortedFileName = "sorted_file.txt";              // создаем имя отсортированного файла
    rename(mergedFileName.c_str(), sortedFileName.c_str()); // переименовываем
    
    return sortedFileName;
}

// проверка файла на предмет отсортированности и наличия всех чисел из исходного файла
bool testResultIsCorrect(string fileName, string sortedFileName, int maxValue) {
    
    int* frequencies = new int[maxValue];
    for (int i = 0; i < maxValue; i++)
        frequencies[i] = 0;
    
    ifstream fin1(fileName);
    int value;
    while (fin1 >> value) {
        frequencies[value] += 1;
    }
    
    ifstream fin2(sortedFileName);
    while (fin2 >> value) {
        frequencies[value] -= 1;
    }
    
    for (int i = 0; i < maxValue; i++)
        if (frequencies[value] != 0)
            return false;
    
    ifstream fin3(sortedFileName);
    int prevValue = -1;
    while (fin3 >> value) {
        if (prevValue > value)
            return false;
        else
            prevValue = value;
    }
    
    return true;
}

int main() {
    
    initRand();
    
    string fileName = "file.txt";
    
    int numberOfValues = 1030; // кол-во чисел в исходном файле
    int maxValue = 5000;       // генерируем случайные числа в пределах этого значения
    
    generateRandomFile(fileName, numberOfValues, maxValue);
    
    cout << "Исходный файл:" << endl;
    printFile(fileName, 200);
    
    string sortedFileName = fileMergeSort(fileName);
    
    cout << "Отсортированный файл:" << endl;
    printFile(sortedFileName, 200);
    
    cout << "Проверка показала, что сортировка файла ";
    if (testResultIsCorrect(fileName, sortedFileName, maxValue))
        cout << "прошла успешно!";
    else
        cout << "содержит ошибки!";
    cout << endl << endl;
    
    return 0;
}
