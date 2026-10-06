import SwiftUI

struct MainView: View {
    var body: some View {
        TabView {
            Tab {
                NewsView()
                    .ignoresSafeArea()
            } label: {
                Label("News", systemImage: "newspaper")
            }
            
            Tab {
                MeetingsView()
                    .ignoresSafeArea()
            } label: {
                Label("Meetings", systemImage: "calendar")
            }
        }
    }
}
