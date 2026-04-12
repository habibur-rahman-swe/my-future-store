# my-future-store

#### DispatcherServlet কী?
- DispatcherServlet হলো একটি সার্ভলেট যা সব রিকোয়েস্ট প্রথমে এখানে আসে এবং এটি সেই রিকোয়েস্টকে উপযুক্ত কন্ট্রোলার, ভিউ ইত্যাদিতে ফরোয়ার্ড করে। এটি স্প্রিং MVC-এর হৃদয়স্বরূপ।

```
DispatcherServlet কীভাবে কাজ করে? (স্টেপ বাই স্টেপ)

Browser Request 
    ↓
DispatcherServlet (ফ্রন্ট কন্ট্রোলার)
    ↓
HandlerMapping (কে হ্যান্ডল করবে?)
    ↓
HandlerAdapter (কন্ট্রোলার কল করো)
    ↓
Controller (বিজনেস লজিক)
    ↓
Model & View (ডাটা + ভিউ নাম)
    ↓
ViewResolver (আসল ভিউ ফাইল খুঁজে বের করো)
    ↓
View (JSP/HTML রেন্ডার করো)
    ↓
Response to Browser
```
---