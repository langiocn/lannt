/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lucbat;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;

/**
 *
 * @author Laptop
 */
public class Demo extends javax.swing.JFrame {

    Vector<String> vocab = new Vector<>();
    Vector<String>bk = new Vector<>(); // Chua cac tu don co van bang, khong dau
    Vector<String>bs = new Vector<>();//Chua cac tu don co van bang, co dau
    Vector<String> wd = new Vector<>();// Chua cac tu dung dau cau va thoa man luat bang trac cua cau 6 va 8
    //Vector<String> wd1 = new Vector<>();
    //Doc file bat.txt va bat1.txt
    LinkedHashMap<String, LinkedHashMap<String, Double>> f1 = new LinkedHashMap<>();
    LinkedHashMap<String, Vector<Double>> f2 = new LinkedHashMap<>();
    // Doc cac tu, xac suat, trong cua tu
        
    LinkedHashMap<String, Vector<Double>> gr1 = new LinkedHashMap<>();//Luu tu, mot vecto chua xac suat va trong so 
    LinkedHashMap<String, Vector<Double>> gr2 = new LinkedHashMap<>();
    LinkedHashMap<String, Vector<Double>> gr3 = new LinkedHashMap<>();
    //Doc file van bang, trac trong tieng viet
        
    LinkedHashSet<String> vb = new LinkedHashSet<>();//Tat ca cac tu don co van bang
    LinkedHashSet<String> vt = new LinkedHashSet<>();// Tat ca cac tu don co van trac
    Vector<Vector<String>> vb1 = new Vector<>();//Luu cac vector cua tung cap van bang
    Vector<String> tap = new Vector<>();//Luu tat ca cac van bang
    Vector<String> vb2 = new Vector<>();//Luu tat ca van trac
    LinkedHashSet<String> check = new LinkedHashSet<>();
    /**
     * Creates new form Demo
     */
    public Demo() throws FileNotFoundException, IOException {
        initComponents();
        setLocationRelativeTo(null);
        BufferedImage img = null;
        img = ImageIO.read(new File("lib/ptitb.jpg"));
        Image dimg = img.getScaledInstance(tfBK.getWidth(), tfBK.getHeight(),
        Image.SCALE_SMOOTH);
        tfBK.setIcon(new ImageIcon(dimg));
        
        appendToPane(tp, "***ĐỒ ÁN TỐT NGHIỆP***\n\n", new Color(167, 3, 0));
        appendToPane(tp, "GVHD: TS.Ngô Xuân Bách\n", new Color(58, 122, 23));
        appendToPane(tp, "SVTH: Nguyễn Thị Làn\n", new Color(58, 122, 23));
        appendToPane(tp, "Lớp:  D14HTTT4\n", new Color(58, 122, 23));
        appendToPane(tp, "Khóa: 2014-2019\n", new Color(58, 122, 23));
        appendToPane(tp, "HTĐT: ĐHCQ\n", new Color(58, 122, 23));
        tp.setEditable(false);
        Scanner in = new Scanner(new File("lib/vocab.txt"));// Doc tu dien truyen kieu
        Scanner ins = new Scanner(new File("lib/VBANG.txt"));// Cac tu dung dau cau va thoa man van luat bang trac cau 6 va 8 
        //Scanner ins1 = new Scanner(new File("VTRAC.txt"));
        Scanner bkhong = new Scanner(new File("lib/fbang.txt"));// Cac tu don co van bang, khong dau
        Scanner bsac = new Scanner(new File("lib/fsac.txt"));// Cac tu don co van bang, co dau
        double tt,tt1;
        Scanner first1 = new Scanner(new File("lib/first1.txt"));
        Scanner first2 = new Scanner(new File("lib/first2.txt"));
        String tm;
        while(first1.hasNext()){
            tm = first1.nextLine();
            String p[] = tm.split(" ");
            if(f1.containsKey(p[0])){
                LinkedHashMap<String, Double> ttt = f1.get(p[0]);
                ttt.put(p[1], Double.parseDouble(p[2]));
                f1.put(p[0], ttt);
            }
            else{
                LinkedHashMap<String, Double> ttt = new LinkedHashMap<>();               
                ttt.put(p[1], Double.parseDouble(p[2]));
                f1.put(p[0], ttt);
            }
        }
        
        while(first2.hasNext()){
            tm = first2.nextLine();
            String p[] = tm.split(" ");
            Vector<Double> v = new Vector<>();
            v.add(Double.parseDouble(p[1]));
            v.add(Double.parseDouble(p[2]));
            f2.put(p[0], v);
        }
        String s;
        while(in.hasNext()){
            s = in.next();
            vocab.add(s);
        }
        while(ins.hasNext()){
            s = ins.next();
            wd.add(s);
        }
        //doc van bang khong, bang sac
        while(bkhong.hasNext()){
            bk.add(bkhong.next());
        }
        while(bsac.hasNext()){
            bs.add(bsac.next());
        }
        
        
       /* while(ins1.hasNext()){
            s = ins1.next();
            wd1.add(s);
        }*/
        Scanner in1 = new Scanner(new File("lib/1gram.txt"));// Chua mo hinh ngon ngu 1 gram
        Scanner in2 = new Scanner(new File("lib/2gram.txt"));//Chua mo hinh ngon ngu 2 gram
        Scanner in3 = new Scanner(new File("lib/3gram.txt"));//Chua mo hinh ngon ngu 3 gram
        String l1,l2,l3,l4,l5;//mot dong trong file
        while(in1.hasNext()){
            l1 = in1.next();
            l2 = in1.next();
            l3 = in1.next();
            Vector<Double> v = new Vector<>();
            v.add(Double.parseDouble(l1));
            v.add(Double.parseDouble(l3));
            gr1.put(l2, v);
        }
        while(in2.hasNext()){
            l1 = in2.next();
            l2 = in2.next();
            l3 = in2.next();
            l4 = in2.next();
            Vector<Double> v = new Vector<>();
            v.add(Double.parseDouble(l1));
            v.add(Double.parseDouble(l4));
            gr2.put(l2+" "+l3, v);
        }
        while(in3.hasNext()){
            l1 = in3.next();
            l2 = in3.next();
            l3 = in3.next();
            l4 = in3.next();
            Vector<Double> v = new Vector<>();
            v.add(Double.parseDouble(l1));
            gr3.put(l2+" "+l3+" "+l4, v);
        }
        Scanner bv = new Scanner(new File("lib/VB.txt"));
        Scanner tv = new Scanner(new File("lib/VT.txt"));
        while(bv.hasNext()){
            vb.add(bv.next());
        }
        while(tv.hasNext()){
            vt.add(tv.next());
        }
        Scanner bv1 = new Scanner(new File("lib/VanBang.txt"));//Chua cac van bang, di theo cap tung dong
        Scanner tv1 = new Scanner(new File("lib/VanTrac.txt"));//Chua tat ca van trac
        String lin;
        while(bv1.hasNext()){
            lin = bv1.nextLine();
            Vector<String> v = new Vector<>();
            if(lin.contains(" ")&&lin!=null){
                String par[] = lin.split(" ");
                for(String i:par){
                    v.add(i);
                    tap.add(i);
                }
            }
            else{
                v.add(lin);
                tap.add(lin);
            }
            
            vb1.add(v);
         }
        while(tv1.hasNext()){
            lin = tv1.next();
            vb2.add(lin);
        }
       
   
    
    }

    private void appendToPane(JTextPane tp, String msg, Color c)
    {
        StyleContext sc = StyleContext.getDefaultStyleContext();
        AttributeSet aset = sc.addAttribute(SimpleAttributeSet.EMPTY, StyleConstants.Foreground, c);

        aset = sc.addAttribute(aset, StyleConstants.FontFamily, "Lucida Console");
        aset = sc.addAttribute(aset, StyleConstants.Alignment, StyleConstants.ALIGN_JUSTIFIED);

        int len = tp.getDocument().getLength();
        tp.setCaretPosition(len);
        tp.setCharacterAttributes(aset, false);
        tp.replaceSelection(msg);
       // tp.setBackground(Color.BLUE);
    }
     // Tim tu dau cau bat
    //LinkedHashMap<Vector1<String>, Double> lk
    // trong do: v1[0] chua tu o cau 8, v1[1] chua tu o cau 6, double chua P(v1[1]/v1[0]
    public String timtu( LinkedHashMap<String, LinkedHashMap<String, Double>> f1,
            LinkedHashMap<String, Vector<Double>> f2,Vector<String> v){
        String s1="";
        double max1 = -6000000;
        for(String nhan : f2.keySet()){
            if(nhan.length()>1&&!check.contains(nhan)){
                Vector<Double> vect = f2.get(nhan);
                LinkedHashMap<String, Double> ds = f1.get(nhan);
                double d = vect.get(0);
                for(String s : v){
                    if(ds.containsKey(s)){
                        d+=ds.get(s);
                    }
                    else d+=vect.get(1);
                }
                if(d>max1){
                    max1 = d;
                    s1 = nhan;
                    //System.out.println(s1+" "+max1);
                }
            }
           
        }
        check.add(s1);
       
        return s1;
    }
    public  Vector<String> timvan(Vector<String> vb2,Vector<Vector<String>> vb1,Vector<String>tap,String s) throws FileNotFoundException{
        //LinkedHashSet<String> vb1 = new LinkedHashSet<>();
        // Trich rut cac van trong tu s
        String s1=s.charAt(s.length()-1)+"";
        String s2="0";
        if(s.length()>1) s2 = s.charAt(s.length()-2)+s1;
        String s3="0",s4="0";
        if(s.length()>2){
            s3 = s.charAt(s.length()-3)+s2;
            if(s.length()>3){
                s4=s.charAt(s.length()-4)+s3;
            }
        }
        String van="";
        //Kiem tra van


        if(tap.contains(s4)) van=s4;     
        
        else if(!vb2.contains(s4)&&tap.contains(s3)) van=s3;
        else if(!vb2.contains(s3)&&tap.contains(s2)) van=s2;
        else if(!vb2.contains(s2)&&tap.contains(s1)) {
            van=s1;
        }
        for(int i=0; i<vb1.size(); i++){
            if(vb1.get(i).contains(van)) return vb1.get(i);// Tra ve vector chua van cua tu s
        }
        return null;
    }
    
    //Sinh cau luc
    
   
  
    public  Vector<Vector<String>> luc3(String ini, Vector<String> vocab, LinkedHashSet<String> vb,
            LinkedHashSet<String> vt,LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2, LinkedHashMap<String, Vector<Double>> gr3, int K){
        
        Vector<Vector<String>> kq = new Vector<>();
       // Scanner in = new Scanner(System.in);
        String word = ini.toLowerCase();// Khoi tao tu ban dau, cau luc ban dau sen
        Vector<String> th[] = new Vector[K];
        int jj = dem(word);
        String w1[] = new String[K];
        String w2[] = new String[K];
        String w3[] = new String[K];
        String w12[] = new String[K];
        String w123[] = new String[K];
        String w23[] = new String[K];
        int gd[] = new int[K];
        for(int i=0; i<K; i++){
            th[i] = new Vector<>();
            th[i].add("<s>");
            th[i].add(word);
            w2[i]=word;
            gd[i] = jj+1;
            w1[i]=w3[i]=w12[i]=w23[i]=w123[i]="";
        }
        int count=0;
        while(true){
            double max=-100000;// tim tan so lon nhat
            int kt1=0;
            for(int i=0; i<K; i++){
                if(gd[i]==7) kt1++;  
            }
            if(kt1==K) break;
            count++;
           if(count>30) return null;
           // next luu tu tiep theo, con ket noi tu truoc va tu sau
             
            // Su dung tree map de uu tien lay 3 gram truoc, sau do toi 2 gram va cuoi cung la 1 gram.
            TreeMap<Double, Vector<String>> g1[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g2[]  = new TreeMap[K];
            TreeMap<Double, Vector<String>> g3[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g0[] = new TreeMap[K];
            for(int i=0; i<K; i++){
                g0[i] = new TreeMap<>();
                g1[i] = new TreeMap<>();
                g2[i] = new TreeMap<>();
                g3[i] = new TreeMap<>();
            }
            LinkedHashSet<String> ds = new LinkedHashSet<>();
            for(String s1:vocab){
                int kt2 = 0;
                for(int i=0; i<K; i++){
                    if(gd[i]<7){
                        int sotu = dem(s1);// dem so tu cua s1
                        boolean kt = false;
                        if(gd[i]==2&&sotu==1){
                            if(vb.contains(s1)) kt=true;//tu thu hai la van bang
                        }
                        else if(gd[i]==2&&sotu<3){
                            String part[] = s1.split("_");
                            if(vb.contains(part[0])) kt=true;//tu thu 2 la van bang
                        }
                        else if(gd[i]==2&&sotu>=3){
                            String part[] = s1.split("_");
                            if(vb.contains(part[0])&&vt.contains(part[2])) kt=true;//tu thu hai la van bang, tu t4 la van trac
                        }
                        else if(gd[i]==3&&sotu==1) kt=true;//tu thu 3 la tu do
                        else if(gd[i]==3&&sotu<=3){
                            String part[] = s1.split("_");
                            if(vt.contains(part[1])) kt=true;// tu thu 4 la van trac
                        }
                        else if(gd[i]==3&&sotu==4){
                            String part[] = s1.split("_");
                            if(vt.contains(part[1])&&vb.contains(part[3])) kt=true;//tu t4 la van trac va t6 la van bang
                        }
                        else if(gd[i]==4&&sotu==1){
                            if(vt.contains(s1)) kt=true;//t4 la van trac
                        }
                        else if(gd[i]==4&&sotu==2){
                            String part[]=s1.split("_");
                            if(vt.contains(part[0])) kt=true;//t4 la van trac
                        }
                        else if(gd[i]==4&&sotu==3){
                            String part[] = s1.split("_");
                            if(vt.contains(part[0])&&vb.contains(part[2])) kt=true;//t4 la van trac va t6 la van bang
                        }
                        else if(gd[i]==5&&sotu==1) kt=true;//t5 tu do
                        else if(gd[i]==5&&sotu==2){
                            String part[] = s1.split("_");
                            if(vb.contains(part[1])) kt=true;//t6 la van bang
                        }
                        else if(gd[i]==6&&sotu==1){
                            if(vb.contains(s1)) kt=true;//t6 la van bang
                        }
                        if(kt==false) kt2=1;
                    }
                }
                if(kt2==0) ds.add(s1);
                
            }
            
            for(int i=0; i<K; i++){
                if(gd[i]<7){
                    for(String s1:ds){
                        w23[i] = w2[i]+" "+s1;//2 gram; w1 = se; w2 = word; w3 = s1
                        //kiem tra xem gr2 co chua "con" khong
                        w123[i] = w1[i] + " " + w23[i];//3 gram
                        w12[i] = w1[i]+" "+w2[i];
                        double po=max;
                        if(gr3.containsKey(w123[i])){
                           // System.out.println(con1);
                            po = gr3.get(w123[i]).get(0);
                            Vector<String> g30 = new Vector<>();
                            if(!g3[i].containsKey(po)){
                                g30.add(s1);
                                g3[i].put(po,g30 );
                            }
                            else{
                                g30 = g3[i].get(po);
                                if(!g30.contains(s1)) g30.add(s1);
                                g3[i].put(po, g30);
                            }
                        }
                        else{
                            double po1=max;
                            int k0=0;
                            if(gr2.containsKey(w23[i])){
                                //ppo = p(wd2|wd1) if(bigram exists)= p_2(wd1,wd2); 
                                po1 = gr2.get(w23[i]).get(0);//lay xac suat cua con trong gr2
                                k0=1;

                            }
                            //neu gr2 khong chua "con"

                            else if(gr1.containsKey(w2[i])&&gr1.containsKey(s1)){
                                po1 = gr1.get(w2[i]).get(1)+gr1.get(s1).get(0);//po = p(wd2|wd1)=bo_wt_1(wd1)*p_1(wd2)

                            }
                            if(gr2.containsKey(w12[i])){
                                po = gr2.get(w12[i]).get(1)+po1;
                                Vector<String> g20 = new Vector<>();
                                if(!g2[i].containsKey(po)){
                                    g20.add(s1);
                                    g2[i].put(po, g20);
                                }
                                else{
                                    g20 = g2[i].get(po);
                                    if(!g20.contains(s1)) g20.add(s1);
                                    g2[i].put(po, g20);
                                }
                            }
                            else{
                                po = po1;
                                
                                if(k0==1){
                                    Vector<String> g10 = new Vector<>();
                                    if(!g1[i].containsKey(po)){
                                        g10.add(s1);
                                        g1[i].put(po, g10);
                                    }
                                    else{
                                        g10 = g1[i].get(po);
                                        if(!g10.contains(s1)) g10.add(s1);
                                        g1[i].put(po, g10);
                                    }
                                }
                                
                                else if(k0==0){
                                    Vector<String> goo = new Vector<>();
                                    if(!g0[i].containsKey(po)){
                                        goo.add(s1);
                                        g0[i].put(po, goo);
                                    }
                                    else{
                                        goo = g0[i].get(po);
                                        if(!goo.contains(s1)) goo.add(s1);
                                        g0[i].put(po, goo);
                                    }
                                }
                            }
                    }
                }
                }
            }
            Vector<String> phu[][] = new Vector[K][K];
            for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    phu[i][j]=new Vector<>();
                }
            }
            Vector<String> gg3 = new Vector<>();
            Vector<String> gg2 = new Vector<>();
            Vector<String> gg1 = new Vector<>();
            Vector<String> gg0 = new Vector<>();
             for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    Vector<String> vp = new Vector<>();
                    for(int h=0; h<th[i].size(); h++){
                        vp.add(th[i].get(h));
                    }
                    gg3 = new Vector<>();
                    gg2 = new Vector<>();
                    gg1 = new Vector<>();
                    gg0 = new Vector<>();
                    phu[i][j].addAll(vp);
                   if(g3[i].size()>0) gg3 = g3[i].get(g3[i].lastKey());
                   else if(g2[i].size()>0) gg2 = g2[i].get(g2[i].lastKey());
                   else if(g1[i].size()>0) gg1 = g1[i].get(g1[i].lastKey());
                   else if(g0[i].size()>0) gg0 = g0[i].get(g0[i].lastKey());
                    if(gg3.size()>0){  
                        phu[i][j].add(gg3.firstElement());
                        gg3.remove(gg3.firstElement());   
                        if(gg3.size()==0) g3[i].remove(g3[i].lastKey());
                       // else g3[i].get(g3[i].lastKey()).remove(g3[i].get(g3[i].lastKey()).firstElement());
                    }
                    else if(gg2.size()>0){ 
                        phu[i][j].add(gg2.firstElement());
                        gg2.remove(gg2.firstElement());
                        if(gg2.size()==0) g2[i].remove(g2[i].lastKey());
                       // else g2[i].get(g2[i].lastKey()).remove(g2[i].get(g2[i].lastKey()).firstElement());
                    }
                    else if(gg1.size()>0){ 
                        phu[i][j].add(gg1.firstElement());
                        gg1.remove(gg1.firstElement());
                        if(gg1.size()==0) g1[i].remove(g1[i].lastKey());
                       // else g1[i].get(g1[i].lastKey()).remove(g1[i].get(g1[i].lastKey()).firstElement());
                    }
                    else if(gg0.size()>0){    
                        phu[i][j].add(gg0.firstElement());
                        gg0.remove(gg0.firstElement());
                        if(gg0.size()==0) g0[i].remove(g0[i].lastKey());
                       // else g0[i].get(g0[i].lastKey()).remove(g0[i].get(g0[i].lastKey()).firstElement());
                    }
                }
                
            }
            
             double po1=0.0;
             HashMap<Vector<String>, Double> tr1 = new HashMap<>();
             for(int i=0; i<K; i++){
                 for(int j=0; j<K; j++){
                     if(phu[i][j].size()>0&&dem(phu[i][j])<8){
                         
                         po1 = xs(gr1, gr2, gr3, phu[i][j]);

                         if(!tr1.containsKey(phu[i][j])) tr1.put(phu[i][j],po1);
                     }
                 }
             }
             
            Map tr = sortByValue(tr1);
            

             for(int i=0; i<K; i++){
                 th[i].clear();
                 Vector<String> v = new Vector<>();
                 Iterator ir = tr.keySet().iterator();
                 Vector<String> ss=new Vector<>();
                 while(ir.hasNext()) ss =  (Vector<String>) ir.next();
                 th[i].addAll(ss);
                 tr.remove(ss);
                 w1[i] = th[i].get(th[i].size()-2);
                 w2[i] = th[i].get(th[i].size()-1);
                 gd[i] = dem(th[i]);
             }
            /* for(int i=0; i<K; i++){
                 System.out.println(th[i]);
             }*/
             
        }
       
        for(int i=0; i<K; i++){
           kq.add(th[i]);
        }
       // kq.add(re);
        return kq;    
            
                
            
    }
     
    public  Vector<String> best(LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2, LinkedHashMap<String, Vector<Double>> gr3,Vector<Vector<String>>kq){
        Vector<String> re=new Vector<>();
        double mx=-100000;
        double xss;
        for(int i=0; i<kq.size(); i++){
           // kq.add(th[i]);
           xss = xs(gr1, gr2, gr3, kq.get(i));
           if(xss>mx){
               re = new Vector<>();
               re.addAll(kq.get(i));
               mx = xss;
           }
        }
        return re;
  
    }
   
    public double xs(LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2, LinkedHashMap<String, Vector<Double>> gr3,
            Vector<String> vec){
        double d = -1000000,d1=0.0;
        for(int i=0; i<=vec.size()-3; i++){
            d1 = gr3(gr1,gr2,gr3,vec.get(i),vec.get(i+1),vec.get(i+2));
            if(d==-1000000) d=d1;
            else d +=d1;
        }
        return d;
    }
    public double gr3(LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2, LinkedHashMap<String, Vector<Double>> gr3,
            String w1, String w2, String w3){
        String w23,w12,w123;
        w23 = w2+" "+w3;//2 gram; w1 = se; w2 = word; w3 = s1
        //kiem tra xem gr2 co chua "con" khong
        w123 = w1 + " " + w23;//3 gram
        w12 = w1+" "+w2;
        double po=-1000000;
        if(gr3.containsKey(w123)){
           // System.out.println(con1);
            po = gr3.get(w123).get(0);

        }
        else{
            double po1=-1000000;
            int k0=0;
            if(gr2.containsKey(w23)){
                //ppo = p(wd2|wd1) if(bigram exists)= p_2(wd1,wd2); 
                po1 = gr2.get(w23).get(0);//lay xac suat cua con trong gr2
                k0=1;

            }
            //neu gr2 khong chua "con"

            else if(gr1.containsKey(w2)&&gr1.containsKey(w3)){
                po1 = gr1.get(w2).get(1)+gr1.get(w3).get(0);//po = p(wd2|wd1)=bo_wt_1(wd1)*p_1(wd2)

            }
            if(gr2.containsKey(w12)){
                po = gr2.get(w12).get(1)+po1;

            }
            else{
                po = po1;

            }
    }
        return po;
    }
    public int dem(Vector<String> c){
        int d=0,tem;
        for(String t:c){
            tem=1;
            for(int i=0; i<t.length(); i++){
                if(t.charAt(i)=='_') tem++;
            }
            d+=tem;
        }
        return d;
    }
    
    // Xet tri, big, uni
    
    
    
    
     public String reW(String sen){
        //Loai bo dau "_"
        String sen1="";
        for(int i=0; i<sen.length(); i++){
            if(sen.charAt(i)!='_'&&sen.charAt(i)!=' ') sen1=sen1+sen.charAt(i);
            else sen1=sen1+" ";
        }
        String senf=Character.toUpperCase(sen1.charAt(0))+"";
        for(int i=1; i<sen1.length(); i++){
            senf=senf+sen1.charAt(i);
        }
        return senf;
    }
    
    public Vector<Vector<String>> bat8(Vector<String> vb2,Vector<Vector<String>> vb1,
            Vector<String>tap,Vector<String> wd, Vector<String> vocab, LinkedHashSet<String> vb,
            LinkedHashSet<String> vt,LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2,LinkedHashMap<String, Vector<Double>> gr3, Vector<String>van, String ro,
            Vector<String>bk, Vector<String> bs, String first, int K) throws FileNotFoundException{
  
        // Scanner in = new Scanner(System.in);
        String word = first;// Khoi tao tu ban dau, cau luc ban dau sen
        Vector<Vector<String>> ds = new Vector<>();
        Vector<String> th[] = new Vector[K];
        int jj = dem(word);
        String w1[] = new String[K];
        String w2[] = new String[K];
        String w3[] = new String[K];
        String w12[] = new String[K];
        String w123[] = new String[K];
        String w23[] = new String[K];
        String lu[] = new String[K];
        int gd[] = new int[K];
        for(int i=0; i<K; i++){
            th[i] = new Vector<>();
            th[i].add("<s>");
            th[i].add(word);
            w2[i]=word;
            gd[i] = jj+1;
            w1[i]=w3[i]=w12[i]=w23[i]=w123[i]=lu[i]="";
        }
        int lan=1,lap = 0;
        while(true){
            double max=-100000;// tim tan so lon nhat
            int kt1=0;
            for(int i=0; i<K; i++){
                if(gd[i]==10) kt1++;  
            }
            if(kt1==K) break;
            if(lap>30) return null;           
            lap++;
           // next luu tu tiep theo, con ket noi tu truoc va tu sau
             
            // Su dung tree map de uu tien lay 3 gram truoc, sau do toi 2 gram va cuoi cung la 1 gram.
            TreeMap<Double, Vector<String>> g1[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g2[]  = new TreeMap[K];
            TreeMap<Double, Vector<String>> g3[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g0[] = new TreeMap[K];
            for(int i=0; i<K; i++){
                g0[i] = new TreeMap<>();
                g1[i] = new TreeMap<>();
                g2[i] = new TreeMap<>();
                g3[i] = new TreeMap<>();
            }
           

            for(int i=0; i<K; i++){
                if(gd[i]<9){
                    for(String s1:vocab){                   
                        if(!th[i].contains(s1)){
                            int sotu=dem(s1);
                            boolean kt=false;
                            if(gd[i]==2&&sotu==1){
                                if(vb.contains(s1)) kt=true;//t2 la van bang
                            }
                            else if(gd[i]==2&&sotu==2){
                                String pa[] = s1.split("_");
                                if(vb.contains(pa[0])) kt=true;//t2 la van bang
                            }
                            else if(gd[i]==2&&sotu==3){
                                String pa[] = s1.split("_");
                                if(vb.contains(pa[0])&&vt.contains(pa[2])) kt=true;//t2 la van bang va t4 la van trac
                            }
                            else if(gd[i]==3&&sotu==1){
                                kt=true;//t3 tu do
                            }
                            else if(gd[i]==3&&sotu==2){
                             //   System.out.println(s1);
                                String pa[] = s1.split("_");
                                if(vt.contains(pa[1])) kt=true;//t4 la van trac
                            }
                           /* else if(gd[i]==3&&sotu==4){
                                String pa[] = s1.split("_");
                                //t4 la van trac, t6 phai van voi van cua cau luc va khong trung voi tu thu 6 cua cau luc
                                if(!pa[3].equals(ro)&&vt.contains(pa[1])&&check(vb2,vb1,tap,van, pa[3])){
                                    kt=true;
                                }
                            }*/

                            else if(gd[i]==4&&sotu==1){
                                if(vt.contains(s1)) kt=true;//t4 la van trac
                            }
                           /* else if(gd[i]==4&&sotu==2){
                                String pa[] = s1.split("_");
                                if(vt.contains(pa[0])) kt=true;//t4 la van trac, t5 tu do
                            }*/
                            /*else if(gd[i]==4&&sotu<5){
                                String pa[] = s1.split("_");
                                if(!pa[2].equals(ro)&&vt.contains(pa[0])&&check(vb2,vb1,tap,van, pa[2])) kt=true;
                                //t4 la van trac, t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6
                            }*/
                            else if(gd[i]==5&&sotu==1) kt=true;
                            else if(gd[i]==5&&sotu<4){
                                String pa[] = s1.split("_");
                                if(!pa[1].equals(ro)&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6
                            }

                            else if(gd[i]==5&&sotu==4){
                                String pa[] = s1.split("_");    

                               //t6 la bang khong thanh, t8 la bang co thanh
                                if(bk.contains(pa[1])){
                                    if(!pa[1].equals(ro)&&bs.contains(pa[3])&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                    //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6 va t8 la van bang va doi thanh 
                                    //voi t6 cua cau 8
                                }
                                //t6 la bang co thanh, t8 la bang khong thanh
                                else{
                                    if(!pa[1].equals(ro)&&bk.contains(pa[3])&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                    //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6 va t8 la van bang va doi thanh 
                                    //voi t6 cua cau 8
                                }
                        }

                        else if(gd[i]==6&&sotu==1){

                            if(!s1.equals(ro)&&check(vb2,vb1,tap,van, s1)){
                                kt=true;
                            }
                        }
                        else if(gd[i]==6&&sotu<3){
                            String pa[] = s1.split("_");
                            if(!pa[0].equals(ro)&&check(vb2,vb1,tap,van, pa[0])){
                                kt=true;
                            }
                        }
                        else if(gd[i]==6&&sotu==3){
                            String pa[] = s1.split("_");


                            if(bk.contains(pa[0])){
                                if(!pa[0].equals(ro)&&bs.contains(pa[2])&&check(vb2,vb1,tap,van, pa[0])) kt=true;
                            }
                            else{
                                if(!pa[0].equals(ro)&&bk.contains(pa[2])&&check(vb2,vb1,tap,van, pa[0])) kt=true;
                            }
                        }
                        else if(gd[i]==7&&sotu==1) kt=true;
                        else if(gd[i]==7&&sotu==2){
                            String pa[] = s1.split("_");
                           // if(bk.contains(lu))
                           if(bk.contains(lu[i])){
                               if(bs.contains(pa[1])) kt=true;
                           }
                           else if(bk.contains(pa[1])) kt=true;
                        }
                        else if(gd[i]==8&&sotu==1){
                            if(bk.contains(lu[i])){
                                if(bs.contains(s1)) kt=true;
                            }
                            else if(bk.contains(s1)) kt=true;
                        }
                            if(kt==true){
                                w23[i] = w2[i]+" "+s1;//2 gram; w1 = se; w2 = word; w3 = s1
                            //kiem tra xem gr2 co chua "con" khong
                            w123[i] = w1[i] + " " + w23[i];//3 gram
                            w12[i] = w1[i]+" "+w2[i];
                            double po=max;
                            double end = max;
                            if(gd[i]+dem(s1)==7){
                                if(gr2.containsKey(s1+" </s>")) end = gr2.get(s1+" </s>").get(0);
                                else end = gr1.get(s1).get(1) + gr1.get("</s>").get(0);
                            }
                            else end = 0;
                            if(gr3.containsKey(w123[i])){
                               // System.out.println(con1);
                                po = gr3.get(w123[i]).get(0)+end;
                                Vector<String> g30 = new Vector<>();
                                if(!g3[i].containsKey(po)){
                                    g30.add(s1);
                                    g3[i].put(po,g30 );
                                }
                                else{
                                    g30 = g3[i].get(po);
                                    if(!g30.contains(s1)) g30.add(s1);
                                    g3[i].put(po, g30);
                                }
                            }
                            else{
                                double po1=max;
                                int k0=0;
                                if(gr2.containsKey(w23[i])){
                                    //ppo = p(wd2|wd1) if(bigram exists)= p_2(wd1,wd2); 
                                    po1 = gr2.get(w23[i]).get(0);//lay xac suat cua con trong gr2
                                    k0=1;

                                }
                                //neu gr2 khong chua "con"

                                else if(gr1.containsKey(w2[i])&&gr1.containsKey(s1)){
                                    po1 = gr1.get(w2[i]).get(1)+gr1.get(s1).get(0);//po = p(wd2|wd1)=bo_wt_1(wd1)*p_1(wd2)

                                }
                                if(gr2.containsKey(w12[i])){
                                    po = gr2.get(w12[i]).get(1)+po1+end;
                                    Vector<String> g20 = new Vector<>();
                                    if(!g2[i].containsKey(po)){
                                        g20.add(s1);
                                        g2[i].put(po, g20);
                                    }
                                    else{
                                        g20 = g2[i].get(po);
                                        if(!g20.contains(s1)) g20.add(s1);
                                        g2[i].put(po, g20);
                                    }
                                }
                                else{
                                    po = po1+end;

                                    if(k0==1){
                                        Vector<String> g10 = new Vector<>();
                                        if(!g1[i].containsKey(po)){
                                            g10.add(s1);
                                            g1[i].put(po, g10);
                                        }
                                        else{
                                            g10 = g1[i].get(po);
                                            if(!g10.contains(s1)) g10.add(s1);
                                            g1[i].put(po, g10);
                                        }
                                    }

                                    else if(k0==0){
                                        Vector<String> goo = new Vector<>();
                                        if(!g0[i].containsKey(po)){
                                            goo.add(s1);
                                            g0[i].put(po, goo);
                                        }
                                        else{
                                            goo = g0[i].get(po);
                                            if(!goo.contains(s1)) goo.add(s1);
                                            g0[i].put(po, goo);
                                        }
                                    }
                                }
                        }
                            }
                        }
                    }
                }
                
            }

           
            
           /* for(int i=0; i<K; i++){
                if(gd[i]<9){
                    for(String s1:ds){
                        
                }
                }
            }*/
           
            
            Vector<String> phu[][] = new Vector[K][K];
            for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    phu[i][j]=new Vector<>();
                }
            }
            
            Vector<String> gg3 = new Vector<>();
            Vector<String> gg2 = new Vector<>();
            Vector<String> gg1 = new Vector<>();
            Vector<String> gg0 = new Vector<>();
             for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    Vector<String> vp = new Vector<>();
                    for(int h=0; h<th[i].size(); h++){
                        vp.add(th[i].get(h));
                    }
                    gg3 = new Vector<>();
                    gg2 = new Vector<>();
                    gg1 = new Vector<>();
                    gg0 = new Vector<>();
                  phu[i][j].addAll(vp);
                   if(g3[i].size()>0) gg3 = g3[i].get(g3[i].lastKey());
                   else if(g2[i].size()>0) gg2 = g2[i].get(g2[i].lastKey());
                   else if(g1[i].size()>0) gg1 = g1[i].get(g1[i].lastKey());
                   else if(g0[i].size()>0) gg0 = g0[i].get(g0[i].lastKey());
                    if(gg3.size()>0){    
                       // phu[i][j].addAll(vp);
                        phu[i][j].add(gg3.firstElement());
                        gg3.remove(gg3.firstElement());
                        if(gg3.size()==0) g3[i].remove(g3[i].lastKey());
                    }
                    else if(gg2.size()>0){ 
                      // phu[i][j].addAll(vp);
                        phu[i][j].add(gg2.firstElement());
                        gg2.remove(gg2.firstElement());
                        if(gg2.size()==0) g2[i].remove(g2[i].lastKey());
                    }
                    else if(gg1.size()>0){ 
                       // phu[i][j].addAll(vp);
                        phu[i][j].add(gg1.firstElement());
                        gg1.remove(gg1.firstElement());
                        if(gg1.size()==0) g1[i].remove(g1[i].lastKey());
                    }
                    else if(gg0.size()>0){    
                      //  phu[i][j].addAll(vp);
                        phu[i][j].add(gg0.firstElement());
                        gg0.remove(gg0.firstElement());
                        if(gg0.size()==0) g0[i].remove(g0[i].lastKey());
                    }
                }
                
            }
            
             double po1=0.0;
             HashMap<Vector<String>, Double> tr1 = new HashMap<>();
             for(int i=0; i<K; i++){
                 for(int j=0; j<K; j++){
                     if(dem(phu[i][j])==9) phu[i][j].add("</s>");
                     if(phu[i][j].size()>0&&dem(phu[i][j])<11){
                         
                         po1 = xs(gr1, gr2, gr3, phu[i][j]);

                         if(!tr1.containsKey(phu[i][j])) tr1.put(phu[i][j],po1);
                     }
                 }
             }
             
            Map tr = sortByValue(tr1);
            
             for(int i=0; i<K; i++){
                 th[i].clear();
                 Vector<String> v = new Vector<>();
                 Iterator ir = tr.keySet().iterator();
                 Vector<String> ss=new Vector<>();
                 while(ir.hasNext()) ss =  (Vector<String>) ir.next();
                 th[i].addAll(ss);
                 tr.remove(ss);
                 w1[i] = th[i].get(th[i].size()-2);
                 w2[i] = th[i].get(th[i].size()-1);
                 gd[i] = dem(th[i]);
                 if(gd[i]>=7){
                     int d1=0;
                     Vector<String> tem1 = new Vector<>();
                     for(String te : th[i]){
                         if(!te.contains("_")) tem1.add(te);
                         else{
                             String p[] = te.split("_");
                             for(String p1:p) tem1.add(p1);
                         }
                     }
                     
                     lu[i] = tem1.get(6);

                 }
                 
             }
            /*for(int i=0; i<K; i++){
                 System.out.println(th[i]);
             }*/
              
             
             
             
        }
       /* for(int i=0; i<K; i++){
            if(dem(th[i])==10) ds.add(th[i]);
        }*/
       
      
        for(int i=0; i<K; i++){
           ds.add(th[i]);
          
        }
         
        return ds;
        
             
        }
    
     public Vector<Vector<String>> bat81(Vector<String> vb2,Vector<Vector<String>> vb1,
            Vector<String>tap,Vector<String> wd, Vector<String> vocab, LinkedHashSet<String> vb,
            LinkedHashSet<String> vt,LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2,LinkedHashMap<String, Vector<Double>> gr3, Vector<String>van, String ro,
            Vector<String>bk, Vector<String> bs, String first, int K) throws FileNotFoundException{
  
        // Scanner in = new Scanner(System.in);
        String word = first;// Khoi tao tu ban dau, cau luc ban dau sen
        Vector<Vector<String>> ds = new Vector<>();
        Vector<String> th[] = new Vector[K];
        int jj = dem(word);
        String w1[] = new String[K];
        String w2[] = new String[K];
        String w3[] = new String[K];
        String w12[] = new String[K];
        String w123[] = new String[K];
        String w23[] = new String[K];
        String lu[] = new String[K];
        int gd[] = new int[K];
        for(int i=0; i<K; i++){
            th[i] = new Vector<>();
            th[i].add("<s>");
            th[i].add(word);
            w2[i]=word;
            gd[i] = jj+1;
            w1[i]=w3[i]=w12[i]=w23[i]=w123[i]=lu[i]="";
        }
        int lan=1,lap = 0;
        while(true){
            double max=-100000;// tim tan so lon nhat
            int kt1=0;
            for(int i=0; i<K; i++){
                if(gd[i]==10) kt1++;  
            }
            if(kt1==K) break;
            lap++;
           if(lap>30) return null;           

           // next luu tu tiep theo, con ket noi tu truoc va tu sau
             
            // Su dung tree map de uu tien lay 3 gram truoc, sau do toi 2 gram va cuoi cung la 1 gram.
            TreeMap<Double, Vector<String>> g1[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g2[]  = new TreeMap[K];
            TreeMap<Double, Vector<String>> g3[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g0[] = new TreeMap[K];
            for(int i=0; i<K; i++){
                g0[i] = new TreeMap<>();
                g1[i] = new TreeMap<>();
                g2[i] = new TreeMap<>();
                g3[i] = new TreeMap<>();
            }
           

            for(int i=0; i<K; i++){
                if(gd[i]<9){
                    for(String s1:vocab){                   
                        if(!th[i].contains(s1)){
                            int sotu=dem(s1);
                            boolean kt=false;
                            if(gd[i]==2&&sotu==1){
                                if(vb.contains(s1)) kt=true;//t2 la van bang
                            }
                            else if(gd[i]==2&&sotu==2){
                                String pa[] = s1.split("_");
                                if(vb.contains(pa[0])) kt=true;//t2 la van bang
                            }
                            else if(gd[i]==2&&sotu==3){
                                String pa[] = s1.split("_");
                                if(vb.contains(pa[0])&&vt.contains(pa[2])) kt=true;//t2 la van bang va t4 la van trac
                            }
                            else if(gd[i]==3&&sotu==1){
                                kt=true;//t3 tu do
                            }
                            else if(gd[i]==3&&sotu==2){
                             //   System.out.println(s1);
                                String pa[] = s1.split("_");
                                if(vt.contains(pa[1])) kt=true;//t4 la van trac
                            }
                           /* else if(gd[i]==3&&sotu==4){
                                String pa[] = s1.split("_");
                                //t4 la van trac, t6 phai van voi van cua cau luc va khong trung voi tu thu 6 cua cau luc
                                if(!pa[3].equals(ro)&&vt.contains(pa[1])&&check(vb2,vb1,tap,van, pa[3])){
                                    kt=true;
                                }
                            }*/

                            else if(gd[i]==4&&sotu==1){
                                if(vt.contains(s1)) kt=true;//t4 la van trac
                            }
                           /* else if(gd[i]==4&&sotu==2){
                                String pa[] = s1.split("_");
                                if(vt.contains(pa[0])) kt=true;//t4 la van trac, t5 tu do
                            }*/
                            /*else if(gd[i]==4&&sotu<5){
                                String pa[] = s1.split("_");
                                if(!pa[2].equals(ro)&&vt.contains(pa[0])&&check(vb2,vb1,tap,van, pa[2])) kt=true;
                                //t4 la van trac, t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6
                            }*/
                            else if(gd[i]==5&&sotu==1) kt=true;
                            else if(gd[i]==5&&sotu<4){
                                String pa[] = s1.split("_");
                                if(!pa[1].equals(ro)&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6
                            }

                            else if(gd[i]==5&&sotu==4){
                                String pa[] = s1.split("_");    

                               //t6 la bang khong thanh, t8 la bang co thanh
                                if(bk.contains(pa[1])){
                                    if(!pa[1].equals(ro)&&bs.contains(pa[3])&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                    //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6 va t8 la van bang va doi thanh 
                                    //voi t6 cua cau 8
                                }
                                //t6 la bang co thanh, t8 la bang khong thanh
                                else{
                                    if(!pa[1].equals(ro)&&bk.contains(pa[3])&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                    //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6 va t8 la van bang va doi thanh 
                                    //voi t6 cua cau 8
                                }
                        }

                        else if(gd[i]==6&&sotu==1){

                            if(!s1.equals(ro)&&check(vb2,vb1,tap,van, s1)){
                                kt=true;
                            }
                        }
                        else if(gd[i]==6&&sotu<3){
                            String pa[] = s1.split("_");
                            if(!pa[0].equals(ro)&&check(vb2,vb1,tap,van, pa[0])){
                                kt=true;
                            }
                        }
                        else if(gd[i]==6&&sotu==3){
                            String pa[] = s1.split("_");


                            if(bk.contains(pa[0])){
                                if(!pa[0].equals(ro)&&bs.contains(pa[2])&&check(vb2,vb1,tap,van, pa[0])) kt=true;
                            }
                            else{
                                if(!pa[0].equals(ro)&&bk.contains(pa[2])&&check(vb2,vb1,tap,van, pa[0])) kt=true;
                            }
                        }
                        else if(gd[i]==7&&sotu==1) kt=true;
                        else if(gd[i]==7&&sotu==2){
                            String pa[] = s1.split("_");
                           // if(bk.contains(lu))
                           if(bk.contains(lu[i])){
                               if(bs.contains(pa[1])) kt=true;
                           }
                           else if(bk.contains(pa[1])) kt=true;
                        }
                        else if(gd[i]==8&&sotu==1){
                            if(bk.contains(lu[i])){
                                if(bs.contains(s1)) kt=true;
                            }
                            else if(bk.contains(s1)) kt=true;
                        }
                            if(kt==true){
                                w23[i] = w2[i]+" "+s1;//2 gram; w1 = se; w2 = word; w3 = s1
                            //kiem tra xem gr2 co chua "con" khong
                            w123[i] = w1[i] + " " + w23[i];//3 gram
                            w12[i] = w1[i]+" "+w2[i];
                            double po=max;
                            double end = max;
                            if(gd[i]+dem(s1)==7){
                                if(gr2.containsKey(s1+" </s>")) end = gr2.get(s1+" </s>").get(0);
                                else end = gr1.get(s1).get(1) + gr1.get("</s>").get(0);
                            }
                            else end = 0;
                            if(gr3.containsKey(w123[i])){
                               // System.out.println(con1);
                                po = gr3.get(w123[i]).get(0)+end;
                                Vector<String> g30 = new Vector<>();
                                if(!g3[i].containsKey(po)){
                                    g30.add(s1);
                                    g3[i].put(po,g30 );
                                }
                                else{
                                    g30 = g3[i].get(po);
                                    if(!g30.contains(s1)) g30.add(s1);
                                    g3[i].put(po, g30);
                                }
                            }
                            else{
                                double po1=max;
                                int k0=0;
                                if(gr2.containsKey(w23[i])){
                                    //ppo = p(wd2|wd1) if(bigram exists)= p_2(wd1,wd2); 
                                    po1 = gr2.get(w23[i]).get(0);//lay xac suat cua con trong gr2
                                    k0=1;

                                }
                                //neu gr2 khong chua "con"

                                else if(gr1.containsKey(w2[i])&&gr1.containsKey(s1)){
                                    po1 = gr1.get(w2[i]).get(1)+gr1.get(s1).get(0);//po = p(wd2|wd1)=bo_wt_1(wd1)*p_1(wd2)

                                }
                                if(gr2.containsKey(w12[i])){
                                    po = gr2.get(w12[i]).get(1)+po1+end;
                                    Vector<String> g20 = new Vector<>();
                                    if(!g2[i].containsKey(po)){
                                        g20.add(s1);
                                        g2[i].put(po, g20);
                                    }
                                    else{
                                        g20 = g2[i].get(po);
                                        if(!g20.contains(s1)) g20.add(s1);
                                        g2[i].put(po, g20);
                                    }
                                }
                                else{
                                    po = po1+end;

                                    if(k0==1){
                                        Vector<String> g10 = new Vector<>();
                                        if(!g1[i].containsKey(po)){
                                            g10.add(s1);
                                            g1[i].put(po, g10);
                                        }
                                        else{
                                            g10 = g1[i].get(po);
                                            if(!g10.contains(s1)) g10.add(s1);
                                            g1[i].put(po, g10);
                                        }
                                    }

                                    else if(k0==0){
                                        Vector<String> goo = new Vector<>();
                                        if(!g0[i].containsKey(po)){
                                            goo.add(s1);
                                            g0[i].put(po, goo);
                                        }
                                        else{
                                            goo = g0[i].get(po);
                                            if(!goo.contains(s1)) goo.add(s1);
                                            g0[i].put(po, goo);
                                        }
                                    }
                                }
                        }
                            }
                        }
                    }
                }
                
            }

           
            
           /* for(int i=0; i<K; i++){
                if(gd[i]<9){
                    for(String s1:ds){
                        
                }
                }
            }*/
           
            
            Vector<String> phu[][] = new Vector[K][K];
            for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    phu[i][j]=new Vector<>();
                }
            }
            
            Vector<String> gg3 = new Vector<>();
            Vector<String> gg2 = new Vector<>();
            Vector<String> gg1 = new Vector<>();
            Vector<String> gg0 = new Vector<>();
             for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    Vector<String> vp = new Vector<>();
                    for(int h=0; h<th[i].size(); h++){
                        vp.add(th[i].get(h));
                    }
                    gg3 = new Vector<>();
                    gg2 = new Vector<>();
                    gg1 = new Vector<>();
                    gg0 = new Vector<>();
               //   phu[i][j].addAll(vp);
                   if(g3[i].size()>0) gg3 = g3[i].get(g3[i].lastKey());
                   else if(g2[i].size()>0) gg2 = g2[i].get(g2[i].lastKey());
                   else if(g1[i].size()>0) gg1 = g1[i].get(g1[i].lastKey());
                   else if(g0[i].size()>0) gg0 = g0[i].get(g0[i].lastKey());
                    if(gg3.size()>0){    
                       phu[i][j].addAll(vp);
                        phu[i][j].add(gg3.firstElement());
                        gg3.remove(gg3.firstElement());
                        if(gg3.size()==0) g3[i].remove(g3[i].lastKey());
                    }
                    else if(gg2.size()>0){ 
                      phu[i][j].addAll(vp);
                        phu[i][j].add(gg2.firstElement());
                        gg2.remove(gg2.firstElement());
                        if(gg2.size()==0) g2[i].remove(g2[i].lastKey());
                    }
                    else if(gg1.size()>0){ 
                       phu[i][j].addAll(vp);
                        phu[i][j].add(gg1.firstElement());
                        gg1.remove(gg1.firstElement());
                        if(gg1.size()==0) g1[i].remove(g1[i].lastKey());
                    }
                    else if(gg0.size()>0){    
                       phu[i][j].addAll(vp);
                        phu[i][j].add(gg0.firstElement());
                        gg0.remove(gg0.firstElement());
                        if(gg0.size()==0) g0[i].remove(g0[i].lastKey());
                    }
                }
                
            }
            
             double po1=0.0;
             HashMap<Vector<String>, Double> tr1 = new HashMap<>();
             for(int i=0; i<K; i++){
                 for(int j=0; j<K; j++){
                     if(dem(phu[i][j])==9) phu[i][j].add("</s>");
                     if(phu[i][j].size()>0&&dem(phu[i][j])<11){
                         
                         po1 = xs(gr1, gr2, gr3, phu[i][j]);

                         if(!tr1.containsKey(phu[i][j])) tr1.put(phu[i][j],po1);
                     }
                 }
             }
             
            Map tr = sortByValue(tr1);
            
             for(int i=0; i<K; i++){
                 th[i].clear();
                 Vector<String> v = new Vector<>();
                 Iterator ir = tr.keySet().iterator();
                 Vector<String> ss=new Vector<>();
                 while(ir.hasNext()) ss =  (Vector<String>) ir.next();
                 th[i].addAll(ss);
                 tr.remove(ss);
                 w1[i] = th[i].get(th[i].size()-2);
                 w2[i] = th[i].get(th[i].size()-1);
                 gd[i] = dem(th[i]);
                 if(gd[i]>=7){
                     int d1=0;
                     Vector<String> tem1 = new Vector<>();
                     for(String te : th[i]){
                         if(!te.contains("_")) tem1.add(te);
                         else{
                             String p[] = te.split("_");
                             for(String p1:p) tem1.add(p1);
                         }
                     }
                     
                     lu[i] = tem1.get(6);

                 }
                 
             }
            /*for(int i=0; i<K; i++){
                 System.out.println(th[i]);
             }*/
              
             
             
             
        }
       /* for(int i=0; i<K; i++){
            if(dem(th[i])==10) ds.add(th[i]);
        }*/
       
       
        for(int i=0; i<K; i++){
           ds.add(th[i]);
           
        }
      //  ds.add(re);         
        return ds;
        
             
        }
    
    
    //Dem so tu cua mot chuoi s
    public static int dem(String s){
        int i=1;
        for(int j=0; j<s.length(); j++){
            if(s.charAt(j)=='_'||s.charAt(j)==' ') i++;
        }
        return i;
    }
    
    //Kiem tra van cho tu t6 cua cau luc va t6 cua cau bat
    public boolean check(Vector<String> vb2,Vector<Vector<String>> vb1,Vector<String>tap,Vector<String> v, String s) throws FileNotFoundException{

      //  System.out.println(v+" "+s);
       Vector<String> val = timvan(vb2,vb1, tap, s);//tim vector van cua s
        if(val!=null){
            for(String s1:v){
                if(val.contains(s1)) return true;
            }
        }
            
        return false;
    }
   
    public  <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());
        

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }

        return result;
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        tfC6 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        ta = new javax.swing.JTextArea();
        btnST = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        tfC8 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        tfK6 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        tfK8 = new javax.swing.JTextField();
        tfBK = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tp = new javax.swing.JTextPane();
        rtri = new javax.swing.JRadioButton();
        rbig = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Câu 6");

        ta.setColumns(20);
        ta.setRows(5);
        jScrollPane1.setViewportView(ta);

        btnST.setText("Sinh thơ");
        btnST.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSTActionPerformed(evt);
            }
        });

        jLabel2.setText("Câu 8");

        tfC8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfC8ActionPerformed(evt);
            }
        });

        jLabel3.setText("Nhập K (câu 6)");

        tfK6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfK6ActionPerformed(evt);
            }
        });

        jLabel4.setText("Nhập K (câu 8)");

        tp.setBackground(new java.awt.Color(255, 102, 102));
        jScrollPane2.setViewportView(tp);

        rtri.setText("Trigram");
        rtri.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rtriMouseClicked(evt);
            }
        });

        rbig.setText("Bigram");
        rbig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rbigMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfBK, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(12, 12, 12)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel2)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(tfC8, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(jLabel1)
                                                        .addGap(29, 29, 29)
                                                        .addComponent(tfC6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                                .addGap(56, 56, 56))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(rtri)
                                                .addGap(66, 66, 66)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel3)
                                                    .addComponent(jLabel4))
                                                .addGap(26, 26, 26)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(tfK6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(tfK8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(39, 39, 39)
                                                .addComponent(rbig)))
                                        .addGap(0, 31, Short.MAX_VALUE)))
                                .addGap(21, 21, 21))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(191, 191, 191)
                        .addComponent(btnST)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(tfBK, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(tfK6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1)
                            .addComponent(tfC6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfK8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(tfC8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(rtri)
                            .addComponent(rbig))
                        .addGap(15, 15, 15)
                        .addComponent(btnST)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSTActionPerformed
        try{
            ta.setText("");
            String s = tfC6.getText();
            String c8 = tfC8.getText();
            int K=1, K1=1;
            K = Integer.parseInt(tfK6.getText());
            K1 = Integer.parseInt(tfK8.getText());
    //Test DON
    Vector<Vector<String>> kq = new Vector<>();
    Vector<Vector<String>> kq1 = new Vector<>();
    if(rtri.isSelected()){
        kq = luc3(s,vocab, vb, vt, gr1, gr2,gr3,K);
    if(kq==null){
        kq = luc3(s,vocab, vb, vt, gr1, gr2,gr3,5);
    }
    String luba;
    for(Vector<String> v : kq){                   
        System.out.println(v);       
        String first = timtu(f1,f2,v);
        String lastE = v.get(v.size()-1);
        check.add(first);
        if(lastE.contains("_")){
            String lucb[] = lastE.split("_");
            luba = lucb[lucb.length-1];
        }
        else luba = lastE;
       // System.out.println(timvan(vb2,vb1,tap,luba));
       if(c8==null||c8.equals("")){
           kq1 = bat8(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, first, K1);
           if(kq1==null) kq1 = bat81(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, first, 1);
       }
       else{
           kq1 = bat8(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, c8, K1);
           if(kq1==null) kq1 = bat81(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, c8, 1);
       }
       ta.append("    ");
       String output = v.get(1).substring(0, 1).toUpperCase() + v.get(1).substring(1);
       if(!output.contains("_")) ta.append(output+" ");
            else{
                String pa[] = output.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
            }
       for(int i=2; i<v.size(); i++){
           String ss = v.get(i);
            if(!ss.contains("_")) ta.append(ss+" ");
            else{
                String pa[] = ss.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
           }
       }
        ta.append("\n");
        String output1 = kq1.get(0).get(1).substring(0, 1).toUpperCase() + kq1.get(0).get(1).substring(1);
       if(!output1.contains("_")) ta.append(output1+" ");
            else{
                String pa[] = output1.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
            }
        
        for(int i=2; i<kq1.get(0).size()-1; i++){
           String ss = kq1.get(0).get(i);
            if(!ss.contains("_")) ta.append(ss+" ");
            else{
                String pa[] = ss.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
           }
       }
        ta.append("\n\n");
        
       /* for(Vector<String> v1:kq1){
            ta.append("  "+v.toString());
            ta.append("\n");
            ta.append(v1.toString());
            ta.append("\n");
            System.out.println(v.toString());
            System.out.println(v1);
        }*/
        
        }
   check.clear();
    }
    else if(rbig.isSelected()){
        kq = luc3_big(s,vocab, vb, vt, gr1, gr2,gr3,K);
    if(kq==null){
        kq = luc3_big(s,vocab, vb, vt, gr1, gr2,gr3,5);
    }
    String luba;
    for(Vector<String> v : kq){                   
        System.out.println(v);       
        String first = timtu(f1,f2,v);
        String lastE = v.get(v.size()-1);
        check.add(first);
        if(lastE.contains("_")){
            String lucb[] = lastE.split("_");
            luba = lucb[lucb.length-1];
        }
        else luba = lastE;
       // System.out.println(timvan(vb2,vb1,tap,luba));
       if(c8==null||c8.equals("")){
           kq1 = bat2_big(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, first, K1);
           if(kq1==null) kq1 = bat2_big(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, first, 1);
       }
       else{
           kq1 = bat2_big(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, c8, K1);
           if(kq1==null) kq1 = bat2_big(vb2, vb1, tap, wd, vocab, vb, vt, gr1, gr2, gr3, timvan(vb2,vb1,tap,luba), luba, bk, bs, c8, 1);
       }
       ta.append("    ");
       String output = v.get(1).substring(0, 1).toUpperCase() + v.get(1).substring(1);
       if(!output.contains("_")) ta.append(output+" ");
            else{
                String pa[] = output.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
            }
       for(int i=2; i<v.size(); i++){
           String ss = v.get(i);
            if(!ss.contains("_")) ta.append(ss+" ");
            else{
                String pa[] = ss.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
           }
       }
        ta.append("\n");
        String output1 = kq1.get(0).get(1).substring(0, 1).toUpperCase() + kq1.get(0).get(1).substring(1);
       if(!output1.contains("_")) ta.append(output1+" ");
            else{
                String pa[] = output1.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
            }
        
        for(int i=2; i<kq1.get(0).size(); i++){
           String ss = kq1.get(0).get(i);
            if(!ss.contains("_")) ta.append(ss+" ");
            else{
                String pa[] = ss.split("_");
                for(String pa1:pa){
                    ta.append(pa1+" ");
                }
           }
       }
        ta.append("\n\n");
        
       /* for(Vector<String> v1:kq1){
            ta.append("  "+v.toString());
            ta.append("\n");
            ta.append(v1.toString());
            ta.append("\n");
            System.out.println(v.toString());
            System.out.println(v1);
        }*/
        
        }
    check.clear();
    }
    
        }catch(Exception e){
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnSTActionPerformed

    
    public Vector<Vector<String>> luc3_big(String ini, Vector<String> vocab, LinkedHashSet<String> vb,
            LinkedHashSet<String> vt,LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2, LinkedHashMap<String, Vector<Double>> gr3, int K){
        
        Vector<Vector<String>> kq = new Vector<>();
       // Scanner in = new Scanner(System.in);
        String word = ini.toLowerCase();// Khoi tao tu ban dau, cau luc ban dau sen
        Vector<String> th[] = new Vector[K];
        int jj = dem(word);
        String w1[] = new String[K];
        String w2[] = new String[K];
        String w3[] = new String[K];
        String w12[] = new String[K];
        String w123[] = new String[K];
        String w23[] = new String[K];
        int gd[] = new int[K];
        for(int i=0; i<K; i++){
            th[i] = new Vector<>();
            th[i].add("<s>");
            th[i].add(word);
            w2[i]=word;
            gd[i] = jj+1;
            w1[i]=w3[i]=w12[i]=w23[i]=w123[i]="";
        }
        int lap=0;
        while(true){
            double max=-100000;// tim tan so lon nhat
            int kt1=0;
            for(int i=0; i<K; i++){
                if(gd[i]==7) kt1++;  
            }
            lap++;
            if(lap>30) return null;
            if(kt1==K) break;
           // next luu tu tiep theo, con ket noi tu truoc va tu sau
             
            // Su dung tree map de uu tien lay 3 gram truoc, sau do toi 2 gram va cuoi cung la 1 gram.
            //TreeMap<Double, Vector<String>> g1[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g2[]  = new TreeMap[K];
            TreeMap<Double, Vector<String>> g3[] = new TreeMap[K];
            //TreeMap<Double, Vector<String>> g0[] = new TreeMap[K];
            for(int i=0; i<K; i++){
                g2[i] = new TreeMap<>();
                g3[i] = new TreeMap<>();
            }
            LinkedHashSet<String> ds = new LinkedHashSet<>();
            for(String s1:vocab){
                int kt2 = 0;
                for(int i=0; i<K; i++){
                    if(gd[i]<7){
                        int sotu = dem(s1);// dem so tu cua s1
                        boolean kt = false;
                        if(gd[i]==2&&sotu==1){
                            if(vb.contains(s1)) kt=true;//tu thu hai la van bang
                        }
                        else if(gd[i]==2&&sotu<3){
                            String part[] = s1.split("_");
                            if(vb.contains(part[0])) kt=true;//tu thu 2 la van bang
                        }
                        else if(gd[i]==2&&sotu>=3){
                            String part[] = s1.split("_");
                            if(vb.contains(part[0])&&vt.contains(part[2])) kt=true;//tu thu hai la van bang, tu t4 la van trac
                        }
                        else if(gd[i]==3&&sotu==1) kt=true;//tu thu 3 la tu do
                        else if(gd[i]==3&&sotu<=3){
                            String part[] = s1.split("_");
                            if(vt.contains(part[1])) kt=true;// tu thu 4 la van trac
                        }
                        else if(gd[i]==3&&sotu==4){
                            String part[] = s1.split("_");
                            if(vt.contains(part[1])&&vb.contains(part[3])) kt=true;//tu t4 la van trac va t6 la van bang
                        }
                        else if(gd[i]==4&&sotu==1){
                            if(vt.contains(s1)) kt=true;//t4 la van trac
                        }
                        else if(gd[i]==4&&sotu==2){
                            String part[]=s1.split("_");
                            if(vt.contains(part[0])) kt=true;//t4 la van trac
                        }
                        else if(gd[i]==4&&sotu==3){
                            String part[] = s1.split("_");
                            if(vt.contains(part[0])&&vb.contains(part[2])) kt=true;//t4 la van trac va t6 la van bang
                        }
                        else if(gd[i]==5&&sotu==1) kt=true;//t5 tu do
                        else if(gd[i]==5&&sotu==2){
                            String part[] = s1.split("_");
                            if(vb.contains(part[1])) kt=true;//t6 la van bang
                        }
                        else if(gd[i]==6&&sotu==1){
                            if(vb.contains(s1)) kt=true;//t6 la van bang
                        }
                        if(kt==false) kt2=1;
                    }
                }
                if(kt2==0) ds.add(s1);
                
            }
            
            for(int i=0; i<K; i++){
                if(gd[i]<7){
                    for(String s1:ds){
                        //w1[i] tu dau tien; w2[i] tu thu 2; s1 tu thu 3; 
                        
                        w23[i] = w2[i]+" "+s1;
                        double po = -1000000;
                        if(gr2.containsKey(w23[i])){
                            po = gr2.get(w23[i]).get(0);
                            Vector<String> g30 = new Vector<>();
                            if(!g3[i].containsKey(po)){
                                g30.add(s1);
                                g3[i].put(po,g30 );
                            }
                            else{
                                g30 = g3[i].get(po);
                                if(!g30.contains(s1)) g30.add(s1);
                                g3[i].put(po, g30);
                            }
                        }
                        else if(gr1.containsKey(w2[i])&&gr1.containsKey(s1)){
                            po = gr1.get(w2[i]).get(1) + gr1.get(s1).get(0);
                            Vector<String> g20 = new Vector<>();
                            if(!g2[i].containsKey(po)){
                                g20.add(s1);
                                g2[i].put(po, g20);
                            }
                            else{
                                g20 = g2[i].get(po);
                                if(!g20.contains(s1)) g20.add(s1);
                                g2[i].put(po, g20);
                            }
                        }
                }
                }
            }
            Vector<String> phu[][] = new Vector[K][K];
            for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    phu[i][j]=new Vector<>();
                }
            }
            Vector<String> gg3 = new Vector<>();
            Vector<String> gg2 = new Vector<>();
            Vector<String> gg1 = new Vector<>();
            Vector<String> gg0 = new Vector<>();
             for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    Vector<String> vp = new Vector<>();
                    for(int h=0; h<th[i].size(); h++){
                        vp.add(th[i].get(h));
                    }
                    gg3 = new Vector<>();
                    gg2 = new Vector<>();
                    phu[i][j].addAll(vp);
                   if(g3[i].size()>0) gg3 = g3[i].get(g3[i].lastKey());
                   else if(g2[i].size()>0) gg2 = g2[i].get(g2[i].lastKey());
                    if(gg3.size()>0){ 
                      //  phu[i][j].addAll(vp);
                        phu[i][j].add(gg3.firstElement());
                        gg3.remove(gg3.firstElement());
                        if(gg3.size()==0) g3[i].remove(g3[i].lastKey());
                    }
                    else if(gg2.size()>0){ 
                        //phu[i][j].addAll(vp);
                        phu[i][j].add(gg2.firstElement());
                        gg2.remove(gg2.firstElement());
                        if(gg2.size()==0) g2[i].remove(g2[i].lastKey());
                    }
                    
                }
                
            }
            
             double po1=0.0;
             HashMap<Vector<String>, Double> tr1 = new HashMap<>();
             for(int i=0; i<K; i++){
                 for(int j=0; j<K; j++){
                     if(phu[i][j].size()>0&&dem(phu[i][j])<8){
                         
                         po1 = xs(gr1, gr2, gr3, phu[i][j]);

                         if(!tr1.containsKey(phu[i][j])) tr1.put(phu[i][j],po1);
                     }
                 }
             }
             
            Map tr = sortByValue(tr1);
            

             for(int i=0; i<K; i++){
                 th[i].clear();
                 Vector<String> v = new Vector<>();
                 Iterator ir = tr.keySet().iterator();
                 Vector<String> ss=new Vector<>();
                 while(ir.hasNext()) ss =  (Vector<String>) ir.next();
                 th[i].addAll(ss);
                 tr.remove(ss);
                 w1[i] = th[i].get(th[i].size()-2);
                 w2[i] = th[i].get(th[i].size()-1);
                 gd[i] = dem(th[i]);
             }
        }
        for(int i=0; i<K; i++){
            kq.add(th[i]);
        }
             
        return kq;    
            
                
            
    }
    public Vector<Vector<String>> bat2_big(Vector<String> vb2,Vector<Vector<String>> vb1,
            Vector<String>tap,Vector<String> wd, Vector<String> vocab, LinkedHashSet<String> vb,
            LinkedHashSet<String> vt,LinkedHashMap<String, Vector<Double>> gr1,
            LinkedHashMap<String, Vector<Double>> gr2,LinkedHashMap<String, Vector<Double>> gr3, Vector<String>van, String ro,
            Vector<String>bk, Vector<String> bs, String first, int K) throws FileNotFoundException{
  
        // Scanner in = new Scanner(System.in);
        String word = first;// Khoi tao tu ban dau, cau luc ban dau sen
        Vector<Vector<String>> ds = new Vector<>();
        Vector<String> th[] = new Vector[K];
        int jj = dem(word);
        String w1[] = new String[K];
        String w2[] = new String[K];
        String w3[] = new String[K];
        String w12[] = new String[K];
        String w123[] = new String[K];
        String w23[] = new String[K];
        String lu[] = new String[K];
        int gd[] = new int[K];
        
        for(int i=0; i<K; i++){
            th[i] = new Vector<>();
            th[i].add("<s>");
            th[i].add(word);
            w2[i]=word;
            gd[i] = jj+1;
            w1[i]=w3[i]=w12[i]=w23[i]=w123[i]=lu[i]="";
        }
        int lan=1,lap = 0;
        while(true){
            double max=-100000;// tim tan so lon nhat
            int kt1=0;
            for(int i=0; i<K; i++){
                if(gd[i]==9) kt1++;  
            }
            if(kt1==K) break;
            lap++;
            if(lap>30) return null;

           // next luu tu tiep theo, con ket noi tu truoc va tu sau
             
            // Su dung tree map de uu tien lay 3 gram truoc, sau do toi 2 gram va cuoi cung la 1 gram.
            //TreeMap<Double, Vector<String>> g1[] = new TreeMap[K];
            TreeMap<Double, Vector<String>> g2[]  = new TreeMap[K];
            TreeMap<Double, Vector<String>> g3[] = new TreeMap[K];
           // TreeMap<Double, Vector<String>> g0[] = new TreeMap[K];
            for(int i=0; i<K; i++){
                g2[i] = new TreeMap<>();
                g3[i] = new TreeMap<>();
            }
           

            for(int i=0; i<K; i++){
                if(gd[i]<9){
                    for(String s1:vocab){
                    
                        int sotu=dem(s1);
                        boolean kt=false;
                        if(gd[i]==2&&sotu==1){
                            if(vb.contains(s1)) kt=true;//t2 la van bang
                        }
                        else if(gd[i]==2&&sotu==2){
                            String pa[] = s1.split("_");
                            if(vb.contains(pa[0])) kt=true;//t2 la van bang
                        }
                        else if(gd[i]==2&&sotu==3){
                            String pa[] = s1.split("_");
                            if(vb.contains(pa[0])&&vt.contains(pa[2])) kt=true;//t2 la van bang va t4 la van trac
                        }
                        else if(gd[i]==3&&sotu==1){
                            kt=true;//t3 tu do
                        }
                        else if(gd[i]==3&&sotu==2){
                         //   System.out.println(s1);
                            String pa[] = s1.split("_");
                            if(vt.contains(pa[1])) kt=true;//t4 la van trac
                        }
                        /*else if(gd[i]==3&&sotu==4){
                            String pa[] = s1.split("_");
                            //t4 la van trac, t6 phai van voi van cua cau luc va khong trung voi tu thu 6 cua cau luc
                            if(!pa[3].equals(ro)&&vt.contains(pa[1])&&check(vb2,vb1,tap,van, pa[3])){
                                kt=true;
                            }
                        }*/

                        else if(gd[i]==4&&sotu==1){
                            if(vt.contains(s1)) kt=true;//t4 la van trac
                        }
                        /*else if(gd[i]==4&&sotu==2){
                            String pa[] = s1.split("_");
                            if(vt.contains(pa[0])) kt=true;//t4 la van trac, t5 tu do
                        }*/
                        else if(gd[i]==4&&sotu==1){
                            String pa[] = s1.split("_");
                            if(!pa[2].equals(ro)&&vt.contains(pa[0])&&check(vb2,vb1,tap,van, pa[2])) kt=true;
                            //t4 la van trac, t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6
                        }
                        else if(gd[i]==5&&sotu==1) kt=true;
                        else if(gd[i]==5&&sotu<4){
                            String pa[] = s1.split("_");
                            if(!pa[1].equals(ro)&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                            //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6
                        }

                        else if(gd[i]==5&&sotu==4){
                            String pa[] = s1.split("_");    

                           //t6 la bang khong thanh, t8 la bang co thanh
                            if(bk.contains(pa[1])){
                                if(!pa[1].equals(ro)&&bs.contains(pa[3])&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6 va t8 la van bang va doi thanh 
                                //voi t6 cua cau 8
                            }
                            //t6 la bang co thanh, t8 la bang khong thanh
                            else{
                                if(!pa[1].equals(ro)&&bk.contains(pa[3])&&check(vb2,vb1,tap,van, pa[1])) kt=true;
                                //t6 van voi van cua cau 6 va khong trung voi t6 cua cau 6 va t8 la van bang va doi thanh 
                                //voi t6 cua cau 8
                            }
                    }
                    
                    else if(gd[i]==6&&sotu==1){
                           
                        if(!s1.equals(ro)&&check(vb2,vb1,tap,van, s1)){
                            kt=true;
                        }
                    }
                    else if(gd[i]==6&&sotu<3){
                        String pa[] = s1.split("_");
                        if(!pa[0].equals(ro)&&check(vb2,vb1,tap,van, pa[0])){
                            kt=true;
                        }
                    }
                    else if(gd[i]==6&&sotu==3){
                        String pa[] = s1.split("_");
                        
                        
                        if(bk.contains(pa[0])){
                            if(!pa[0].equals(ro)&&bs.contains(pa[2])&&check(vb2,vb1,tap,van, pa[0])) kt=true;
                        }
                        else{
                            if(!pa[0].equals(ro)&&bk.contains(pa[2])&&check(vb2,vb1,tap,van, pa[0])) kt=true;
                        }
                    }
                    else if(gd[i]==7&&sotu==1) kt=true;
                    else if(gd[i]==7&&sotu==2){
                        String pa[] = s1.split("_");
                       // if(bk.contains(lu))
                       if(bk.contains(lu[i])){
                           if(bs.contains(pa[1])) kt=true;
                       }
                       else if(bk.contains(pa[1])) kt=true;
                    }
                    else if(gd[i]==8&&sotu==1){
                        if(bk.contains(lu[i])){
                            if(bs.contains(s1)) kt=true;
                        }
                        else if(bk.contains(s1)) kt=true;
                    }
                        if(kt==true){
                                                   //w1[i] tu dau tien; w2[i] tu thu 2; s1 tu thu 3; 
                        
                        w23[i] = w2[i]+" "+s1;
                        double po = -1000000;
                        if(gr2.containsKey(w23[i])){
                            po = gr2.get(w23[i]).get(0);
                            Vector<String> g30 = new Vector<>();
                            if(!g3[i].containsKey(po)){
                                g30.add(s1);
                                g3[i].put(po,g30 );
                            }
                            else{
                                g30 = g3[i].get(po);
                                if(!g30.contains(s1)) g30.add(s1);
                                g3[i].put(po, g30);
                            }
                        }
                        else if(gr1.containsKey(w2[i])&&gr1.containsKey(s1)){
                            po = gr1.get(w2[i]).get(1) + gr1.get(s1).get(0);
                            Vector<String> g20 = new Vector<>();
                            if(!g2[i].containsKey(po)){
                                g20.add(s1);
                                g2[i].put(po, g20);
                            }
                            else{
                                g20 = g2[i].get(po);
                                if(!g20.contains(s1)) g20.add(s1);
                                g2[i].put(po, g20);
                            }
                        }
                        }
                    }
                }
                
            }

           
            
           /* for(int i=0; i<K; i++){
                if(gd[i]<9){
                    for(String s1:ds){
                        
                }
                }
            }*/
           
            
            Vector<String> phu[][] = new Vector[K][K];
            for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    phu[i][j]=new Vector<>();
                }
            }
            Vector<String> gg3 = new Vector<>();
            Vector<String> gg2 = new Vector<>();
            Vector<String> gg1 = new Vector<>();
            Vector<String> gg0 = new Vector<>();
             for(int i=0; i<K; i++){
                for(int j=0; j<K; j++){
                    Vector<String> vp = new Vector<>();
                    for(int h=0; h<th[i].size(); h++){
                        vp.add(th[i].get(h));
                    }
                    gg3 = new Vector<>();
                    gg2 = new Vector<>();
                    phu[i][j].addAll(vp);
                   if(g3[i].size()>0) gg3 = g3[i].get(g3[i].lastKey());
                   else if(g2[i].size()>0) gg2 = g2[i].get(g2[i].lastKey());
                    if(gg3.size()>0){ 
                      //  phu[i][j].addAll(vp);
                        phu[i][j].add(gg3.firstElement());
                        gg3.remove(gg3.firstElement());
                        if(gg3.size()==0) g3[i].remove(g3[i].lastKey());
                    }
                    else if(gg2.size()>0){ 
                       // phu[i][j].addAll(vp);
                        phu[i][j].add(gg2.firstElement());
                        gg2.remove(gg2.firstElement());
                        if(gg2.size()==0) g2[i].remove(g2[i].lastKey());
                    }
                    
                }
                
            }
            
             double po1=0.0;
             HashMap<Vector<String>, Double> tr1 = new HashMap<>();
             for(int i=0; i<K; i++){
                 for(int j=0; j<K; j++){
                     if(phu[i][j].size()>0&&dem(phu[i][j])<10){
                         
                         po1 = xs(gr1, gr2, gr3, phu[i][j]);

                         if(!tr1.containsKey(phu[i][j])) tr1.put(phu[i][j],po1);
                     }
                 }
             }
             
            Map tr = sortByValue(tr1);
            
             for(int i=0; i<K; i++){
                 th[i].clear();
                 Vector<String> v = new Vector<>();
                 Iterator ir = tr.keySet().iterator();
                 Vector<String> ss=new Vector<>();
                 while(ir.hasNext()) ss =  (Vector<String>) ir.next();
                 th[i].addAll(ss);
                 tr.remove(ss);
                 w1[i] = th[i].get(th[i].size()-2);
                 w2[i] = th[i].get(th[i].size()-1);
                 gd[i] = dem(th[i]);
                 if(gd[i]>=7){
                     int d1=0;
                     Vector<String> tem1 = new Vector<>();
                     for(String te : th[i]){
                         if(!te.contains("_")) tem1.add(te);
                         else{
                             String p[] = te.split("_");
                             for(String p1:p) tem1.add(p1);
                         }
                     }
                     
                     lu[i] = tem1.get(6);

                 }
                 
             }
              
             
             
             
        }
        for(int i=0; i<K; i++){
            if(dem(th[i])==9) ds.add(th[i]);
        }
       
        return ds;
        
             
        }
    private void tfK6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfK6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfK6ActionPerformed

    private void tfC8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfC8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfC8ActionPerformed

    private void rbigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rbigMouseClicked
    
        rtri.setSelected(false);
    }//GEN-LAST:event_rbigMouseClicked

    private void rtriMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rtriMouseClicked
     
        rbig.setSelected(false);
    }//GEN-LAST:event_rtriMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Demo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Demo().setVisible(true);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Demo.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnST;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JRadioButton rbig;
    private javax.swing.JRadioButton rtri;
    private javax.swing.JTextArea ta;
    private javax.swing.JLabel tfBK;
    private javax.swing.JTextField tfC6;
    private javax.swing.JTextField tfC8;
    private javax.swing.JTextField tfK6;
    private javax.swing.JTextField tfK8;
    private javax.swing.JTextPane tp;
    // End of variables declaration//GEN-END:variables
}
